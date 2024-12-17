package ru.igojig.filefilter.fileprocessing;


import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.statistics.StatisticFactory;
import ru.igojig.filefilter.writers.WriterFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для обработки файлов
 * Обработка осуществляется последовательным вызовом обработчиков: </br>
 * {@link ReadDataProcessor} -> {@link ConvertDataProcessor} -> {@link WriteDataProcessor} ->{@link StatDataProcessor}
 */
@Log4j2
public class DataProcessor {

    private final ProgramArguments programArguments;

    private ReadDataProcessor readDataProcessor;
    private ConvertDataProcessor convertDataProcessor;
    private WriteDataProcessor writeDataProcessor;
    private StatDataProcessor statDataProcessor;

    public DataProcessor(ProgramArguments programArguments) {
        this.programArguments = programArguments;

        init();
        setChain();
    }

    /**
     * Инициализирует классы обработки.
     * При возникновении ошибки открытия файлов на запись программа завершается с закрытием всех открытых потоков
     */
    private void init() {
        readDataProcessor = new ReadDataProcessor(programArguments);
        convertDataProcessor = new ConvertDataProcessor();
        statDataProcessor = new StatDataProcessor(programArguments);
        try {
            writeDataProcessor = new WriteDataProcessor(programArguments);
        } catch (FileFilterIOException e) {
            log.fatal("""
                    Error during open file {} for writing.
                    Cause: {}
                    Exit program.
                    """, e.getAbstractWriter().getPath(), e.getMessage());
            closeWritersAndExit();
        }
    }

    private void setChain() {
        readDataProcessor.next = convertDataProcessor;
        convertDataProcessor.next = writeDataProcessor;
        writeDataProcessor.next = statDataProcessor;
    }

    /**
     * Метод запускает обработку файлов
     */
    public void start() {
        try {
            // первым запускается обработчик чтения файлов, данных еще нет поэтому null
            readDataProcessor.processing(null);
        } catch (FileFilterIOException e) {
            log.fatal("""
                    Error write to {}
                    Cause: {}
                    Exit program.
                    """, e.getAbstractWriter().getPath(), e.getMessage());
            closeAllAndExit();
        }

        // все успешно прошло, закрываем потоки записи
        WriterFactory.closeAll();

        StatisticFactory.showAll(programArguments.getStatisticType());
        printUsedFilesNames();
    }

    /**
     * Метод выводит список файлов, которые были созданы или обновлены
     */
    private void printUsedFilesNames() {
        List<Path> paths = WriterFactory.getUsedPaths();
        String files = paths.stream()
                .map(Path::toString)
                .collect(Collectors.joining("\n"));

        String message = """
                File(s):
                %s
                %s
                """.formatted(files, programArguments.getDoneMessage());

        System.out.println(message);
    }

    /**
     * Метод закрывает все потоки открытые для записи и завершает работу
     */
    private void closeWritersAndExit() {
        WriterFactory.closeAll();
        System.exit(2);
    }

    /**
     * Метод закрывает все открытые потоки и завершает работу
     */
    private void closeAllAndExit() {
        WriterFactory.closeAll();
        readDataProcessor.getDataReader().close();
        System.exit(2);
    }
}

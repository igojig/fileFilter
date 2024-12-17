package ru.igojig.filefilter.processing;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.converter.Convertor;
import ru.igojig.filefilter.exceptions.DataWriteException;
import ru.igojig.filefilter.exceptions.WriterOpenException;
import ru.igojig.filefilter.reader.DataReader;
import ru.igojig.filefilter.statistics.StatisticFactory;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;
import ru.igojig.filefilter.writers.WriterFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для обработки файлов.
 */
@Log4j2
public class ProcessFiles {
    /**
     * Обработанные аргументы командной строки в виде удобном для работы программы
     */
    private final ProgramArguments programArguments;

    /**
     * Объект для чтения входных данных
     */
    private DataReader dataReader;

    /**
     * Конструктор класса. Инициализирует фабрику {@link WriterFactory} именами файлов
     * для соответствующих типов данных.
     * Открывает файлы для записи с нужными параметрами, при ошибке программа завершается.
     *
     * @param programArguments обработанные параметры командной строки {@link ru.igojig.filefilter.args.ProgramArguments}
     */
    public ProcessFiles(ProgramArguments programArguments) {
        this.programArguments = programArguments;
        WriterFactory.initFactory(programArguments.getOutputNamesToClassMap());
        try {
            WriterFactory.openWriters(programArguments.getWriteOptions());
        } catch (WriterOpenException e) {
            log.error("Error open {}, cause: {}", e.getAbstractWriter().getPath(), e.getMessage());
            closeWritersAndExit();
        }
    }

    /**
     * Метод запускает обработку файлов {@link ru.igojig.filefilter.args.ProgramArguments#inputFilenames}.
     * Открывает файл из списка входных файлов на чтение, при ошибке открытия происходит переход к следующему файлу.
     * При ошибке чтения из файла происходит переход к следующему файлу.
     * После прочтения файла закрывает поток связанный с файлом.
     */
    public void process() {
        List<Path> inputs = programArguments.getInputFilenames();

        for (Path path : inputs) {
            dataReader = new DataReader(path, this::processLine);
            try {
                dataReader.open();
            } catch (IOException e) {
                log.error("""
                        Error open file {}: {}
                        Will proceed next input file.
                        """, path, e.getMessage());
                continue;
            }
            log.info("Processing file: {}", path);
            try {
                dataReader.read();
            } catch (IOException e) {
                log.error("""
                        Error read from {}, cause: {}
                        Proceed next input file.
                        """, path, e.getMessage());
                dataReader.close();
                continue;
            }
            dataReader.close();
        }

        // все прошло успешно, закрываем Writers
        WriterFactory.closeAll();

        // выводим статистику
        StatisticFactory.showAll(programArguments.getStatisticType());
        printFilesNames();
    }

    /**
     * Метод обрабатывает прочитанную строку.
     * Вызывается из {@link  DataReader#read()}
     *
     * @param line прочитанная строка
     */
    public void processLine(String line) {
        ReadedObject readedObject = convert(line);
        write(readedObject);
        getStatistics(readedObject);
    }

    /**
     * Метод конвертирует строку в нужный тип данных, вызывая конверторы {@link Convertor#convert(String)}
     *
     * @param line прочитанная из файла строка
     * @return сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    private ReadedObject convert(String line) {
        return Convertor.convert(line);
    }

    /**
     * Метод записывает строку в нужный файл в зависимости от типа данных.
     * При ошибке записи все открытые потоки закрываются, программа завершается.
     *
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    private void write(ReadedObject readedObject) {
        AbstractWriter abstractWriter = WriterFactory.getWriter(readedObject.getType());
        try {
            abstractWriter.write(readedObject);
        } catch (DataWriteException e) {
            log.fatal("Error write to {}, cause: {}", e.getAbstractWriter().getPath(), e.getMessage());
            closeAllAndExit();
        }
    }

    /**
     * Метод собирает статистику в зависимости от типа данных
     *
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    private void getStatistics(ReadedObject readedObject) {
        StatisticFactory.getStat(readedObject.getType(), programArguments.getStatisticType()).accumulate(readedObject);
    }


    /**
     * Метод закрывает все открытые потоки и завершает работу
     */
    private void closeAllAndExit() {
        WriterFactory.closeAll();
        dataReader.close();
        System.exit(2);
    }

    /**
     * Метод закрывает все потоки открытые для записи и завершает работу
     */
    private void closeWritersAndExit() {
        WriterFactory.closeAll();
        System.exit(2);
    }

    /**
     * Метод выводит список файлов, которые были созданы или обновлены
     */
    private void printFilesNames() {
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
}

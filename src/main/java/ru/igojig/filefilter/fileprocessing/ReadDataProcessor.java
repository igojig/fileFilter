package ru.igojig.filefilter.fileprocessing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.reader.DataReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для чтения файлов.
 * Класс читает из файлов и передает прочитанные данные следующему обработчику
 */
@Log4j2
@Getter
@RequiredArgsConstructor
public class ReadDataProcessor extends AbstractDataProcessor {
    /**
     * Обработанные аргументы командной строки в виде удобном для работы программы
     */
    private final ProgramArguments programArguments;

    /**
     * Объект для чтения входных данных
     */
    private DataReader dataReader;

    /**
     * Метод запускает обработку файлов {@link ru.igojig.filefilter.args.ProgramArguments#inputFilenames}.
     * Открывает файл из списка входных файлов на чтение, при ошибке открытия происходит переход к следующему файлу.
     * При ошибке чтения из файла происходит переход к следующему файлу.
     * После прочтения файла закрывает поток связанный с файлом.
     */
    @Override
    public void processing(Object obj) throws FileFilterIOException {
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
    }

    /**
     * Метод обрабатывает прочитанную строку.
     * Вызывается из {@link  DataReader#read()}
     * и передается далее на обработку
     * @param line прочитанная строка
     */
    public void processLine(String line) throws FileFilterIOException {
        if(next!=null){
            next.processing(line);
        }
    }
}

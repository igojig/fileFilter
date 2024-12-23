package ru.igojig.filefilter.reader;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.fileprocessing.ReadDataProcessor;
import ru.igojig.filefilter.system.DataConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Класс для чтения входных файлов {@link ProgramArguments#inputFilenames}
 */
@Data
@Log4j2
public class DataReader {
    /**
     * Файл с исходными данными {@link ProgramArguments#inputFilenames}
     */
    private final Path path;

    /**
     * Потребитель, который будет обрабатывать строку
     */
    private final DataConsumer<String> consumer;
    private BufferedReader bufferedReader;

    /**
     *
     * @param path входной файл {@link ProgramArguments#inputFilenames}
     * @param consumer потребитель данных для обработки строки - {@link ReadDataProcessor#processLine(String)}
     */
    public DataReader(Path path, DataConsumer<String> consumer) {
        this.path = path;
        this.consumer = consumer;
    }

    public void open() throws IOException {
        bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    public void close() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                log.error("""
                        Error closing file {}, cause: {}
                        Continue.
                        """, path, e.getMessage());
            }
        }
    }

    /**
     * Читает строки из файла и отдает потребителю {@code consumer}
     * @throws IOException при ошибке чтения пробрасывает исключение
     */
    public void read() throws IOException, FileFilterIOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // send for fileprocessing
            consumer.accept(line);
        }
    }
}

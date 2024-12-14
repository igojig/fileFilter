package ru.igojig.reader;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.igojig.system.CustomConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@Log4j2
public class DataReader {
    private final Path path;
    private final CustomConsumer consumer;
    private BufferedReader bufferedReader;

    public DataReader(Path path, CustomConsumer consumer) {
        this.path = path;
        this.consumer = consumer;
    }

    public void open() throws IOException {
        bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    public void close() {
        if(bufferedReader!=null){
            try {
                bufferedReader.close();
            } catch (IOException e) {
                log.error("Error closing reader {}, cause: {}", path, e.getMessage());
                log.error("Continue");
            }
        }

    }

    public void read() throws IOException {

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // send for processing
            consumer.consume(line);
        }
    }

}

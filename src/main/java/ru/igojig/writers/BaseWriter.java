package ru.igojig.writers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.igojig.ReadedObject;
import ru.igojig.exceptions.DataWriteException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
@Setter
@Getter
public class BaseWriter {

    protected BufferedWriter writer;
    protected Path path;

    public void open() throws IOException {
//        Files.createDirectories()
        writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
    }


    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                log.error("Error while closing {}. Continue.", path);
            }
        }

    }

    protected void writeObject(ReadedObject readedObject) throws DataWriteException {
        try {
            writer.write(readedObject.getStringValue());
            writer.newLine();
        } catch (IOException e){
            throw new DataWriteException(this, e.getMessage());
        }
    }
}


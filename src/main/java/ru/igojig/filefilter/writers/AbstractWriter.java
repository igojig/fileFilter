package ru.igojig.filefilter.writers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.exceptions.DataWriteException;
import ru.igojig.filefilter.system.ReadedObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Log4j2
@Setter
@Getter
public abstract class AbstractWriter {
    protected boolean isUsed;
    protected BufferedWriter writer;
    protected Path path;

    public abstract void write(ReadedObject readedObject) throws DataWriteException;

    public void open(StandardOpenOption... writeOptions) throws IOException {
//        Files.createDirectories(path.getParent());
        writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, writeOptions);
    }


    public void close() {
        if (writer != null) {
            try {
                writer.close();
                if (Files.size(path) == 0) {
                    Files.delete(path);
                }
            } catch (IOException e) {
                log.error("Error while closing {}. Continue.", path);
            }
        }

    }

    protected void writeObject(ReadedObject readedObject) throws DataWriteException {
        try {
            writer.write(readedObject.getStringValue());
            writer.newLine();
        } catch (IOException e) {
            throw new DataWriteException(this, e.getMessage());
        }
    }
}


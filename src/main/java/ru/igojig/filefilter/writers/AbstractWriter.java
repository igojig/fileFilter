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

/**
 * Базовый класс для записи исходящих данных
 */
@Log4j2
@Setter
@Getter
public abstract class AbstractWriter {
    /**
     * Флаг указывающий, была ли осуществлена хоть одна запись.
     */
    protected boolean isUsed;
    protected BufferedWriter writer;
    /**
     * Файл для записи
     */
    protected Path path;

    /**
     * Метод записывает данные в файл
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     * @throws DataWriteException при ошибке записи выбрасывается {@link ru.igojig.filefilter.exceptions.DataWriteException}
     */
    public abstract void write(ReadedObject readedObject) throws DataWriteException;

    /**
     * Открывает поток для записи
     * @param writeOptions параметры открытия потока {@link ru.igojig.filefilter.args.ProgramArguments#writeOptions}
     * @throws IOException при ошибке открытия потока
     */
    public void open(StandardOpenOption... writeOptions) throws IOException {
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

    /**
     * Метод записывает данные в файл.</br>
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     * @throws DataWriteException при ошибке записи
     */
    protected void writeObject(ReadedObject readedObject) throws DataWriteException {
        try {
            writer.write(readedObject.getStringValue());
            writer.newLine();
        } catch (IOException e) {
            throw new DataWriteException(this, e.getMessage());
        }
    }
}

package ru.igojig.filefilter.writers.impl;

import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;

import java.nio.file.Path;

/**
 * Класс для записи вещественных данных
 */
public class FloatsWriter extends AbstractWriter {

    public FloatsWriter(Path path) {
        this.path = path;
    }

    @Override
    public void write(ReadedObject readedObject) throws FileFilterIOException {
        isUsed = true;
        writeObject(readedObject);
    }
}

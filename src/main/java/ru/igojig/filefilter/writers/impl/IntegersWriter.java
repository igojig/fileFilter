package ru.igojig.filefilter.writers.impl;

import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;

import java.nio.file.Path;

/**
 * Класс для записи целочисленных данных
 */
public class IntegersWriter extends AbstractWriter {

    public IntegersWriter(Path path) {
        this.path = path;
    }

    @Override
    public void write(ReadedObject readedObject) throws FileFilterIOException {
        isUsed = true;
        writeObject(readedObject);
    }
}

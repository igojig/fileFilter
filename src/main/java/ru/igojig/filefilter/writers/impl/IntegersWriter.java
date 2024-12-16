package ru.igojig.filefilter.writers.impl;

import ru.igojig.filefilter.exceptions.DataWriteException;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;

import java.nio.file.Path;

public class IntegersWriter extends AbstractWriter {

    public IntegersWriter(Path path) {
        this.path = path;
    }

    @Override
    public void write(ReadedObject readedObject) throws DataWriteException {
        isUsed = true;
        writeObject(readedObject);
//        System.out.println("IntegersWriter " + readedObject);
    }
}

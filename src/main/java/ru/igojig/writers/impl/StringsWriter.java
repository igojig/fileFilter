package ru.igojig.writers.impl;

import ru.igojig.ReadedObject;
import ru.igojig.exceptions.DataWriteException;
import ru.igojig.writers.BaseWriter;
import ru.igojig.writers.DataWriter;

import java.nio.file.Path;

public class StringsWriter extends BaseWriter implements DataWriter {

    public StringsWriter(Path path){
        this.path=path;
    }

    @Override
    public void write(ReadedObject readedObject) throws DataWriteException {
        writeObject(readedObject);
//        System.out.println("StringsWriter "+ readedObject);
    }
}

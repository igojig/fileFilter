package ru.igojig.writers;

import ru.igojig.ReadedObject;
import ru.igojig.exceptions.DataWriteException;

public interface DataWriter {
   void write(ReadedObject readedObject) throws DataWriteException;
}

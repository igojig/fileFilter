package ru.igojig.filefilter.fileprocessing;

import ru.igojig.filefilter.exceptions.FileFilterIOException;

/**
 * Родитель для всех обработчиков файлов
 */
public abstract class AbstractDataProcessor {
    protected AbstractDataProcessor next;
    public abstract void processing(Object obj) throws FileFilterIOException;
}

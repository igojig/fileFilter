package ru.igojig.filefilter.system;

import ru.igojig.filefilter.exceptions.FileFilterIOException;

/**
 * Кастомный consumer, который умеет кидать исключение
 *
 */
public interface DataConsumer<T> {
    void accept(T t) throws FileFilterIOException;
}

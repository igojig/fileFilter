package ru.igojig.filefilter.exceptions;

import lombok.Getter;
import ru.igojig.filefilter.writers.AbstractWriter;

/**
 * Исключение при ошибке открытия файла для записи
 */
@Getter
public class WriterOpenException extends Exception {

    private final AbstractWriter abstractWriter;
    private final String message;

    /**
     *
     * @param abstractWriter
     * @param message
     */
    public WriterOpenException(AbstractWriter abstractWriter, String message) {
        super(message);
        this.abstractWriter = abstractWriter;
        this.message = message;
    }
}

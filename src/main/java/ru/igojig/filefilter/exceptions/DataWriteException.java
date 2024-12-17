package ru.igojig.filefilter.exceptions;

import lombok.Getter;
import ru.igojig.filefilter.writers.AbstractWriter;

/**
 * Исключение при ошибке записи выходных данных
 */
@Getter
public class DataWriteException extends Exception {
    private final AbstractWriter abstractWriter;
    private final String message;

    /**
     *
     * @param abstractWriter объект бросивший исключение
     * @param message сообщение
     */
    public DataWriteException(AbstractWriter abstractWriter, String message) {
        super(message);
        this.abstractWriter = abstractWriter;
        this.message = message;
    }

}

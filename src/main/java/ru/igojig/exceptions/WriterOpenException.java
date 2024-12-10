package ru.igojig.exceptions;

import lombok.Data;
import lombok.Getter;
import ru.igojig.writers.BaseWriter;

@Getter
public class WriterOpenException extends Exception{

    private final BaseWriter baseWriter;
    private final String message;


    public WriterOpenException(BaseWriter baseWriter, String message) {
        super(message);
        this.baseWriter = baseWriter;
        this.message = message;
    }
}

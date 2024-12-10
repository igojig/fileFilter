package ru.igojig.exceptions;

import lombok.Getter;
import ru.igojig.writers.BaseWriter;

@Getter
public class DataWriteException extends Exception{
    private final BaseWriter baseWriter;
    private final String message;

    public DataWriteException(BaseWriter baseWriter, String message){
        super(message);
        this.baseWriter=baseWriter;
        this.message=message;
    }

}

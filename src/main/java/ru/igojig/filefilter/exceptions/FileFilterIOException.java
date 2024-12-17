package ru.igojig.filefilter.exceptions;

import lombok.Getter;
import ru.igojig.filefilter.writers.AbstractWriter;

@Getter
public class FileFilterIOException extends Exception{
    private final AbstractWriter abstractWriter;
    private final String message;

    public FileFilterIOException(AbstractWriter abstractWriter, String message){
        super(message);
        this.abstractWriter = abstractWriter;
        this.message = message;
    }
}

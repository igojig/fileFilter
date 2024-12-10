package ru.igojig.writers.impl;

import lombok.extern.log4j.Log4j2;
import ru.igojig.ReadedObject;
import ru.igojig.writers.DataWriter;

@Log4j2
public class UnsupportedWriter implements DataWriter {
    @Override
    public void write(ReadedObject readedObject) {
        log.error("writing unsupported value type: {}. Skipping", readedObject);
//        throw new RuntimeException("Unsupported data writer: " + o.getClass() + " "+ o.toString());
    }
}

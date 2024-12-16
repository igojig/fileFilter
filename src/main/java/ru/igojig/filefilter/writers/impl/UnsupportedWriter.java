package ru.igojig.filefilter.writers.impl;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;

@Log4j2
public class UnsupportedWriter extends AbstractWriter {
    @Override
    public void write(ReadedObject readedObject) {
        log.error("writing unsupported value type: {}. Skipping", readedObject);
//        throw new RuntimeException("Unsupported data writer: " + o.getClass() + " "+ o.toString());
    }
}

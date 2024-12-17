package ru.igojig.filefilter.writers.impl;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;

/**
 * Если тип данных неизвестен, то используется этот класс
 */
@Log4j2
public class UnsupportedWriter extends AbstractWriter {
    @Override
    public void write(ReadedObject readedObject) {
        log.error("writing unsupported value type: {}. Skipping", readedObject);
    }
}

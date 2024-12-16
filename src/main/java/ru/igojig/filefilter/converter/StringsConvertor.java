package ru.igojig.filefilter.converter;

import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.system.Strings;

/**
 * Финальный конвертер.
 * Если предыдущие конвертеры не сработали, значит считаем что тип данных - строка
 */
class StringsConvertor extends AbstractConvertor {
    @Override
    public ReadedObject convert(String str) {
        return ReadedObject.builder()
                .stringValue(str)
                .obj(str)
                .type(Strings.class)
                .build();
    }
}

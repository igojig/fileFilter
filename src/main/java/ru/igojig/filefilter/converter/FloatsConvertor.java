package ru.igojig.filefilter.converter;

import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigDecimal;

/**
 * Конвертер строки в вещественный тип ({@code BigDecimal})
 * При неудаче вызывает следующий конвертер.
 */
class FloatsConvertor extends AbstractConvertor {
    /**
     * Метод пытаться преобразовать строку в вещественный тип {@code BigDecimal}. При неудаче вызывает следующий конвертер.
     * @param str прочитанная из файлов строка
     * @return сконвертированная в вещественный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public ReadedObject convert(String str) {
        try {
            BigDecimal val=new BigDecimal(str);
            return ReadedObject.builder()
                    .type(Floats.class)
                    .obj(val)
                    .stringValue(str)
                    .build();
        } catch (NumberFormatException e) {
            return next.convert(str);
        }
    }
}

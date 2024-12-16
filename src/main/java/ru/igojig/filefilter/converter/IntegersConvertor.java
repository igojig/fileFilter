package ru.igojig.filefilter.converter;

import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigInteger;

/**
 * Конвертер строки в целочисленный тип ({@code BigInteger})
 * При неудаче вызывает следующий конвертер.
 */
class IntegersConvertor extends AbstractConvertor {
    /**
     * Метод пытаться преобразовать строку в целочисленный тип данных ({@code BigInteger}). При неудаче передает вызов далее.
     * @param str прочитанная из файлов строка
     * @return сконвертированная в целочисленный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public ReadedObject convert(String str) {
        try {
            BigInteger val = new BigInteger(str);
            return ReadedObject.builder()
                    .stringValue(str)
                    .type(Integers.class)
                    .obj(val)
                    .build();
        } catch (NumberFormatException e) {
            return next.convert(str);
        }
    }
}

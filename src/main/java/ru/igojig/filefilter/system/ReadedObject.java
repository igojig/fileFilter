package ru.igojig.filefilter.system;

import lombok.Builder;
import lombok.Data;

/**
 * Класс, описывающий тип данных прочитанной строки после конвертации {@link ru.igojig.filefilter.converter.Convertor}
 */
@Data
@Builder
public class ReadedObject {
    /**
     * Строковое представление данных
     */
    private final String stringValue;

    /**
     * Указывает, какой тип данных содержит строка </br>-
     * целочисленный {@link ru.igojig.filefilter.system.Integers},
     * вещественный {@link ru.igojig.filefilter.system.Floats},
     * строковый {@link ru.igojig.filefilter.system.Strings}
     */
    private final Class<? extends DataType> type;

    /**
     * Представление сконвертированных данных в виде Object (т.е. BigDecimal, BigInteger, String)
     */
    private final Object obj;
}

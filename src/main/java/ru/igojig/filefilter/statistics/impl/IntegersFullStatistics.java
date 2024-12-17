package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Класс для представления полной статистики для целочисленного типа данных
 */
public class IntegersFullStatistics extends Statistics {

    private BigInteger min;
    private BigInteger max;
    private BigInteger sum = BigInteger.ZERO;

    /**
     * Метод собирает полную статистику для целочисленного типа данных
     * @param readedObject сконвертированная в целочисленный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        BigInteger value = (BigInteger) readedObject.getObj();
        incrementCount();

        if (min == null) {
            min = value;
        } else {
            min = min.min(value);
        }

        if (max == null) {
            max = value;
        } else {
            max = max.max(value);
        }

        sum = sum.add(value);
    }

    /**
     * Метод выводит полную статистику для целочисленного типа данных
     */
    @Override
    public void show() {

        BigDecimal avgBigDecimal = new BigDecimal(sum)
                .divide(BigDecimal.valueOf(count), 8, RoundingMode.HALF_EVEN);

        String longStat = """
                Integers full statistics:
                 Elements: %s
                 Min: %s
                 Max: %s
                 Sum: %s
                 Avg: %s
                """.formatted(count,
                min,
                max,
                sum,
                avgBigDecimal);

        System.out.println(longStat);

    }
}

package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Класс для представления полной статистики для вещественного типа данных
 */
public class FloatsFullStatistics extends Statistics {

    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum = BigDecimal.ZERO;

    /**
     * Метод собирает полную статистику для вещественного типа данных
     * @param readedObject сконвертированная в вещественный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        BigDecimal value = (BigDecimal) readedObject.getObj();
        incrementCount();

        if (min == null) {
            min = value;
        } else {
            min = value.min(min);
        }

        if (max == null) {
            max = value;
        } else {
            max = value.max(max);
        }

        sum = sum.add(value, MathContext.DECIMAL64);
    }

    /**
     * Метод выводит статистику для вещественного типа данных
     */
    @Override
    public void show() {

        BigDecimal avgBigDecimal = sum
                .divide(BigDecimal.valueOf(count), MathContext.DECIMAL64);

        String longStat = """
                Floats full statistics:
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

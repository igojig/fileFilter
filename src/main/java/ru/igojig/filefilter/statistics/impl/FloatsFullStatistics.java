package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.statistics.data.FloatsFullStatData;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Класс собирает полную статистику для вещественного типа данных
 */
public class FloatsFullStatistics extends Statistics {

    /**
     * Данные для полной статистики {@link ru.igojig.filefilter.statistics.data.FloatsFullStatData}
     */
    FloatsFullStatData floatsFullStatData = new FloatsFullStatData();

    /**
     * Метод собирает полную статистику для вещественного типа данных
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        setUsed(true);

        BigDecimal value = (BigDecimal) readedObject.getObj();

        floatsFullStatData.incrementCount();

        BigDecimal min = floatsFullStatData.getMin();
        if (min == null) {
            min = value;
        } else {
            min = value.min(min);
        }
        floatsFullStatData.setMin(min);

        BigDecimal max = floatsFullStatData.getMax();
        if (max == null) {
            max = value;
        } else {
            max = value.max(max);
        }
        floatsFullStatData.setMax(max);

        BigDecimal sum = floatsFullStatData.getSum();
        sum = sum.add(value, MathContext.DECIMAL64);
        floatsFullStatData.setSum(sum);

    }

    /**
     * Метод выводит статистику для вещественного типа данных
     */
    @Override
    public void show() {

        BigDecimal avgBigDecimal = floatsFullStatData.getSum()
                .divide(BigDecimal.valueOf(floatsFullStatData.getCount()), MathContext.DECIMAL64);

        String longStat = """
                Floats full statistics:
                  Elements: %s
                  Min: %s
                  Max: %s
                  Sum: %s
                  Avg: %s
                """.formatted(floatsFullStatData.getCount(),
                floatsFullStatData.getMin().toString(),
                floatsFullStatData.getMax().toPlainString(),
                floatsFullStatData.getSum().toString(),
                avgBigDecimal.toString()
        );

        System.out.println(longStat);
    }


}

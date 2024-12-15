package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Statistics;
import ru.igojig.statistics.data.FloatsFullStatData;

import java.math.BigDecimal;
import java.math.MathContext;

public class FloatsFullStatistics extends Statistics {

    FloatsFullStatData floatsFullStatData = new FloatsFullStatData();


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
                floatsFullStatData.getMax(),
                floatsFullStatData.getSum().toString(),
                avgBigDecimal.toString()
        );

        System.out.println(longStat);
    }


}

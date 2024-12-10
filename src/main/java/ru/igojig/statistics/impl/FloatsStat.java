package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Stat;
import ru.igojig.statistics.data.BaseStatData;
import ru.igojig.statistics.data.FloatsStatData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FloatsStat extends Stat {

    private FloatsStatData floatsStatData = new FloatsStatData();

    @Override
    public BaseStatData getStat() {
        return floatsStatData;
    }

    @Override
    public void accumulate(ReadedObject readedObject) {

        BigDecimal value = (BigDecimal) readedObject.getObj();

        floatsStatData.incrementCount();

        BigDecimal min = floatsStatData.getMin();
        min = value.min(min);
        floatsStatData.setMin(min);

        BigDecimal max = floatsStatData.getMax();
        max = value.max(max);
        floatsStatData.setMax(max);

        BigDecimal sum = floatsStatData.getSum();
        sum = sum.add(value);
        floatsStatData.setSum(sum);

    }

    @Override
    public void showShortStat() {
        String shortStat = "Element count: " + floatsStatData.getCount();
        System.out.println(shortStat);
    }

    @Override
    public void showLongStat() {

        BigDecimal avgBigDecimal = floatsStatData.getSum()
                .divide(BigDecimal.valueOf(floatsStatData.getCount()), 4, RoundingMode.HALF_EVEN);

        String longStat= """
                Element count: %s
                Min: %s
                Max: %s
                Sum: %s
                Avg: %s
                """.formatted(floatsStatData.getCount(),
                floatsStatData.getMin().toPlainString(),
                floatsStatData.getMax().toPlainString(),
                floatsStatData.getSum(),
                avgBigDecimal.toPlainString()
        );

        System.out.println(longStat);

    }
}

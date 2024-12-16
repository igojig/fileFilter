package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.statistics.data.IntegersFullStatData;
import ru.igojig.filefilter.system.ReadedObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegersFullStatistics extends Statistics {

    private final IntegersFullStatData integersFullStatData = new IntegersFullStatData();


    @Override
    public void accumulate(ReadedObject readedObject) {

        setUsed(true);

        BigInteger value = (BigInteger) readedObject.getObj();

        integersFullStatData.incrementCount();

        BigInteger min = integersFullStatData.getMin();
        if (min == null) {
            min = value;
        } else {
            min = min.min(value);
        }
        integersFullStatData.setMin(min);

        BigInteger max = integersFullStatData.getMax();
        if (max == null) {
            max = value;
        } else {
            max = max.max(value);
        }
        integersFullStatData.setMax(max);

        BigInteger sum = integersFullStatData.getSum();
        sum = sum.add(value);
        integersFullStatData.setSum(sum);
    }


    @Override
    public void show() {


        BigDecimal avgBigDecimal = new BigDecimal(integersFullStatData.getSum())
                .divide(BigDecimal.valueOf(integersFullStatData.getCount()), 8, RoundingMode.HALF_EVEN);

        String longStat = """
                Integers full statistics:
                 Elements: %s
                 Min: %s
                 Max: %s
                 Sum: %s
                 Avg: %s
                """.formatted(integersFullStatData.getCount(),
                integersFullStatData.getMin().toString(),
                integersFullStatData.getMax().toString(),
                integersFullStatData.getSum().toString(),
                avgBigDecimal.toPlainString()
        );

        System.out.println(longStat);

    }
}

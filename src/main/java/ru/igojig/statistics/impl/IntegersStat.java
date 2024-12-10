package ru.igojig.statistics.impl;

import lombok.Getter;
import ru.igojig.ReadedObject;
import ru.igojig.statistics.Stat;
import ru.igojig.statistics.data.BaseStatData;
import ru.igojig.statistics.data.IntegersStatData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegersStat extends Stat {

  private   IntegersStatData integersStatData=new IntegersStatData();


    @Override
    public BaseStatData getStat() {
        return integersStatData;
    }

    @Override
    public void accumulate(ReadedObject readedObject) {

        BigInteger value=(BigInteger) readedObject.getObj();

        integersStatData.incrementCount();

        BigInteger min=integersStatData.getMin().min(value);
        integersStatData.setMin(min);

        BigInteger max=integersStatData.getMax().max(value);
        integersStatData.setMax(max);

        BigInteger sum=integersStatData.getSum();
        sum=sum.add(value);
        integersStatData.setSum(sum);
    }

    @Override
    public void showShortStat() {
        String shortStat="Element count: " + integersStatData.getCount();
        System.out.println(shortStat);
    }

    @Override
    public void showLongStat() {


        BigDecimal avgBigDecimal = new BigDecimal(integersStatData.getSum())
                .divide(BigDecimal.valueOf(integersStatData.getCount()), 4, RoundingMode.HALF_EVEN);

        String longStat= """
                Element count: %s
                Min: %s
                Max: %s
                Sum: %s
                Avg: %s
                """.formatted(integersStatData.getCount(),
                integersStatData.getMin().toString(),
                integersStatData.getMax().toString(),
                integersStatData.getSum().toString(),
                avgBigDecimal.toPlainString()
        );

        System.out.println(longStat);
    }
}

package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Statistics;
import ru.igojig.statistics.data.ShortStatData;
import ru.igojig.statistics.data.IntegersFullStatData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegersFullStatistics extends Statistics {

  private final IntegersFullStatData integersFullStatData =new IntegersFullStatData();


    @Override
    public void accumulate(ReadedObject readedObject) {

        super.setUsed(true);

        BigInteger value=(BigInteger) readedObject.getObj();

        integersFullStatData.incrementCount();

        BigInteger min= integersFullStatData.getMin();
        if(min==null){
            min=value;
        } else {
            min=min.min(value);
        }
        integersFullStatData.setMin(min);

        BigInteger max= integersFullStatData.getMax();
        if(max==null){
            max=value;
        } else {
            max=max.max(value);
        }
        integersFullStatData.setMax(max);

        BigInteger sum= integersFullStatData.getSum();
        sum=sum.add(value);
        integersFullStatData.setSum(sum);
    }


    @Override
    public void show() {

        if(isUsed()){
            BigDecimal avgBigDecimal = new BigDecimal(integersFullStatData.getSum())
                    .divide(BigDecimal.valueOf(integersFullStatData.getCount()), 4, RoundingMode.HALF_EVEN);

            String longStat= """
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
}

package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.statistics.data.ShortStatData;
import ru.igojig.filefilter.system.ReadedObject;

public class IntegersShortStatistics extends Statistics {

    private final ShortStatData shortStatData = new ShortStatData();

    @Override
    public void accumulate(ReadedObject readedObject) {
        setUsed(true);
        shortStatData.incrementCount();
    }

    @Override
    public void show() {
        String stat = """
                Integers short statistics:
                 Elements: %s
                """.formatted(shortStatData.getCount());
        System.out.println(stat);
    }
}

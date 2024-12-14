package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Statistics;
import ru.igojig.statistics.data.ShortStatData;

public class IntegersShortStatistics extends Statistics {

    private final ShortStatData shortStatData = new ShortStatData();

    @Override
    public void accumulate(ReadedObject readedObject) {
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

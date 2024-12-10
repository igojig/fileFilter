package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Stat;
import ru.igojig.statistics.data.BaseStatData;
import ru.igojig.statistics.data.StringsStatData;

public class StringsStat extends Stat {

    private StringsStatData stringsStatData=new StringsStatData();

    @Override
    public BaseStatData getStat() {
        return stringsStatData;
    }

    @Override
    public void accumulate(ReadedObject readedObject) {

        String value=readedObject.getStringValue();

        stringsStatData.incrementCount();

        Long min= stringsStatData.getMinLength();
        //TODO  set string stat min max len to Integer
        min=Math.min(min, value.length());
        stringsStatData.setMinLength(min);

        Long max= stringsStatData.getMaxLength();
        max=Math.max(max, value.length());
        stringsStatData.setMaxLength(max);

    }

    @Override
    public void showShortStat() {
        String shortStat="Element count: " + stringsStatData.getCount();
        System.out.println(shortStat);
    }

    @Override
    public void showLongStat() {
        String longStat= """
                Element count: %s,
                Shortest length: %s,
                Longest length: %s
                """.formatted(stringsStatData.getCount(),
                stringsStatData.getMinLength(),
                stringsStatData.getMaxLength()
                );
        System.out.println(longStat);
    }
}

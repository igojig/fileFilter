package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Statistics;
import ru.igojig.statistics.data.StringsFullStatData;

public class StringsFullStatistics extends Statistics {

    private final StringsFullStatData stringsFullStatData =new StringsFullStatData();

    @Override
    public void accumulate(ReadedObject readedObject) {

        super.setUsed(true);

        String value=readedObject.getStringValue();

        stringsFullStatData.incrementCount();

        Integer min= stringsFullStatData.getMinLength();
        if(min==null){
            min=value.length();
        } else {
            min=Math.min(min, value.length());
        }
        stringsFullStatData.setMinLength(min);

        Integer max= stringsFullStatData.getMaxLength();
        if(max==null){
            max=value.length();
        } else {
            max=Math.max(max, value.length());
        }
        stringsFullStatData.setMaxLength(max);

    }


    @Override
    public void show() {
        if(isUsed()) {
            String longStat = """
                    Strings full statistics:
                     Elements: %s,
                     Shortest length: %s,
                     Longest length: %s
                    """.formatted(stringsFullStatData.getCount(),
                    stringsFullStatData.getMinLength(),
                    stringsFullStatData.getMaxLength()
            );
            System.out.println(longStat);
        }
    }
}

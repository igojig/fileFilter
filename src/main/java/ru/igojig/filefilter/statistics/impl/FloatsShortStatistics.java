package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.statistics.data.ShortStatData;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс собирает краткую статистику для вещественного типа данных
 */
public class FloatsShortStatistics extends Statistics {

    /**
     * Данные для краткой статистики
     */
    private final ShortStatData shortStatData = new ShortStatData();


    @Override
    public void accumulate(ReadedObject readedObject) {
        setUsed(true);
        shortStatData.incrementCount();
    }

    @Override
    public void show() {
        String stat = """
                Floats short statistics:
                 Elements: %s
                """.formatted(shortStatData.getCount());
        System.out.println(stat);
    }
}

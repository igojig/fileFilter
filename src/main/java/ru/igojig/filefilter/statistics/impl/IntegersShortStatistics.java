package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс для представления краткой статистики для целочисленного типа данных
 */
public class IntegersShortStatistics extends Statistics {

    /**
     * Метод собирает краткую статистику для целочисленного типа данных
     * @param readedObject сконвертированная в целочисленный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        incrementCount();
    }

    @Override
    public void show() {
        String stat = """
                Integers short statistics:
                 Elements: %s
                """.formatted(count);
        System.out.println(stat);
    }
}

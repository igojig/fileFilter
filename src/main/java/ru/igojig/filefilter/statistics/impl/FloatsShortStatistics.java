package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс для представления краткой статистики для вещественного типа данных
 */
public class FloatsShortStatistics extends Statistics {
    /**
     * Метод собирает краткую статистику для вещественного типа данных
     * @param readedObject сконвертированная в вещественный тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        incrementCount();
    }

    /**
     * Метод выводит краткую статистику для вещественного типа данных
     */
    @Override
    public void show() {
        String stat = """
                Floats short statistics:
                 Elements: %s
                """.formatted(count);
        System.out.println(stat);
    }
}

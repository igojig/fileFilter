package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс представляет краткую статистику для строкового типа данных
 */
public class StringsShortStatistics extends Statistics {
    /**
     * Метод собирает краткую статистику для строкового типа данных
     * @param readedObject сконвертированная в строковый тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {
        incrementCount();
    }

    /**
     * Метод выводит краткую статистику для строкового типа данных
     */
    @Override
    public void show() {
        String stat = """
                Strings full statistics:
                 Elements: %s
                """.formatted(count);
        System.out.println(stat);
    }
}

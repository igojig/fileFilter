package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс представляет полную статистику по строковому типу данных
 */
public class StringsFullStatistics extends Statistics {

    private Integer minLength;
    private Integer maxLength;

    /**
     * Метод собирает полную статистику для строкового типа данных
     * @param readedObject сконвертированная в строковый тип данных строка в виде объекта {@link ReadedObject}
     */
    @Override
    public void accumulate(ReadedObject readedObject) {

        String value = readedObject.getStringValue();

        incrementCount();

        if (minLength == null) {
            minLength = value.length();
        } else {
            minLength = Math.min(minLength, value.length());
        }

        if (maxLength == null) {
            maxLength = value.length();
        } else {
            maxLength = Math.max(maxLength, value.length());
        }
    }

    /**
     * Метод выводит полную статистику по строковому типу данных
     */
    @Override
    public void show() {

        String longStat = """
                Strings full statistics:
                 Elements: %s,
                 Shortest length: %s,
                 Longest length: %s
                """.formatted(count,
                minLength,
                maxLength);

        System.out.println(longStat);
    }

}

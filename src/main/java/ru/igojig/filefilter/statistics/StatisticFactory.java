package ru.igojig.filefilter.statistics;

import ru.igojig.filefilter.args.StatisticType;
import ru.igojig.filefilter.statistics.impl.*;
import ru.igojig.filefilter.system.DataType;
import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для классов по сбору статистики
 */
public class StatisticFactory {
    /**
     * Карта соответствий типа данных - </br>
     * целочисленного({@link ru.igojig.filefilter.system.Integers}),
     * вещественного({@link ru.igojig.filefilter.system.Floats}),
     * строкового({@link ru.igojig.filefilter.system.Strings})</br>
     * с типом статистики ({@link ru.igojig.filefilter.args.StatisticType})
     * и класса, который эту статистику обрабатывает
     */
    private static final Map<Class<? extends DataType>, Map<StatisticType, Statistics>> statMap = new HashMap<>();

    private static final NullStatistics nullStatistic = new NullStatistics();

    static {
        Map<StatisticType, Statistics> floatsMap = Map.of(
                StatisticType.SHORT, new FloatsShortStatistics(),
                StatisticType.FULL, new FloatsFullStatistics());

        Map<StatisticType, Statistics> integersMap = Map.of(
                StatisticType.SHORT, new IntegersShortStatistics(),
                StatisticType.FULL, new IntegersFullStatistics());

        Map<StatisticType, Statistics> stringsMap = Map.of(
                StatisticType.SHORT, new StringsShortStatistics(),
                StatisticType.FULL, new StringsFullStatistics());

        statMap.put(Floats.class, floatsMap);
        statMap.put(Integers.class, integersMap);
        statMap.put(Strings.class, stringsMap);
    }

    /**
     * Возвращает класс обработки статистики
     * @param aClass класс типа данных, которые нужно обработать - </br>
     * целочисленного({@link ru.igojig.filefilter.system.Integers}),
     * вещественного({@link ru.igojig.filefilter.system.Floats}),
     * строкового({@link ru.igojig.filefilter.system.Strings})
     * @param statisticType тип статистики, которую нужно собрать
     * @return класс обработки статистики
     */
    public static Statistics getStat(Class<? extends DataType> aClass, StatisticType statisticType) {
        Map<StatisticType, Statistics> statisticsMap = statMap.get(aClass);
        Statistics statistics = statisticsMap.getOrDefault(statisticType, nullStatistic);
        return statistics;
    }

    /**
     * Выводит статистику по тем типам данных, которые были использованы
     * @param statisticType тип статистики
     */
    public static void showAll(StatisticType statisticType) {
        for (Map<StatisticType, Statistics> value : statMap.values()) {
            if (value.getOrDefault(statisticType, nullStatistic).getCount()!=0) {
                value.get(statisticType).show();
            }
        }
    }
}

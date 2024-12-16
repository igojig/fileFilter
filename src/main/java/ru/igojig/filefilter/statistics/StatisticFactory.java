package ru.igojig.filefilter.statistics;

import ru.igojig.filefilter.args.StatisticType;
import ru.igojig.filefilter.statistics.impl.*;
import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.Strings;

import java.util.HashMap;
import java.util.Map;

public class StatisticFactory {

    private static final Map<Class<?>, Map<StatisticType, Statistics>> statMap = new HashMap<>();
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

    public static Statistics getStat(Class<?> aClass, StatisticType statisticType) {
        Map<StatisticType, Statistics> statisticsMap = statMap.get(aClass);
        Statistics statistics = statisticsMap.getOrDefault(statisticType, nullStatistic);
        return statistics;
    }

    public static void showAll(StatisticType statisticType) {
        for (Map<StatisticType, Statistics> value : statMap.values()) {
            if (value.getOrDefault(statisticType, nullStatistic).isUsed) {
                value.get(statisticType).show();
            }
        }
    }
}

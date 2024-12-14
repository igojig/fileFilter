package ru.igojig.statistics;

import ru.igojig.args.StatisticType;
import ru.igojig.statistics.impl.*;
import ru.igojig.system.Floats;
import ru.igojig.system.Integers;
import ru.igojig.system.Strings;

import java.util.HashMap;
import java.util.Map;

public class StatFactory {

    private static final Map<Class<?>, Map<StatisticType, Statistics>> statMap = new HashMap<>();

    static {
        Map<StatisticType, Statistics> floatsMap = Map.of(StatisticType.SHORT, new FloatsShortStatistics(),
                StatisticType.FULL, new FloatsFullStatistics());
        Map<StatisticType, Statistics> integersMap = Map.of(StatisticType.SHORT, new IntegersShortStatistics(),
                StatisticType.FULL, new IntegersFullStatistics());
        Map<StatisticType, Statistics> stringsMap = Map.of(StatisticType.SHORT, new StringsShortStatistics(),
                StatisticType.FULL, new StringsFullStatistics());

        statMap.put(Floats.class, floatsMap);
        statMap.put(Integers.class, integersMap);
        statMap.put(Strings.class, stringsMap);
    }

    public static Statistics getStat(Class<?> aClass, StatisticType statisticType) {
        Map<StatisticType, Statistics> statisticsMap = statMap.get(aClass);
        Statistics statistics = statisticsMap.get(statisticType);
        return statistics;
    }

    public static void showAll(StatisticType statisticType) {
        if(statisticType!=StatisticType.NONE){
            for (Map<StatisticType, Statistics> value : statMap.values()) {
                value.get(statisticType).show();
            }
        }
    }
}

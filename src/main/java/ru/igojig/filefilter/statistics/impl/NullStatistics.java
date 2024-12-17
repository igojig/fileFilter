package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Класс, который представляет статистику, когда параметр {@link ru.igojig.filefilter.args.ProgramArguments#statisticType}
 * равен {@link ru.igojig.filefilter.args.StatisticType#NONE},
 * т.е. когда не указан ни флаг "-s"{@link ru.igojig.filefilter.args.Arguments#shortStatistic},
 * ни флаг "-f"{@link ru.igojig.filefilter.args.Arguments#fullStatistic}
 */
public class NullStatistics extends Statistics {
    @Override
    public void accumulate(ReadedObject readedObject) {
        //ничего не делаем
    }

    @Override
    public void show() {
        //ничего не делаем
    }
}

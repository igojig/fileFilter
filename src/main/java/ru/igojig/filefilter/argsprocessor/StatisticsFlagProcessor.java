package ru.igojig.filefilter.argsprocessor;

import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.args.StatisticType;

/**
 * Устанавливает режим сбора статистики {@link ru.igojig.filefilter.args.ProgramArguments#statisticType}
 * в зависимости от установленных флагов "-s" {@link Arguments#shortStatistic},
 * "-f" {@link ru.igojig.filefilter.args.Arguments#fullStatistic}
 */
public class StatisticsFlagProcessor extends AbstractProcessor {
    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {
        // если указаны два флага, то тогда полная статистика
        if (arguments.getFullStatistic()) {
            programArguments.setStatisticType(StatisticType.FULL);
        } else if (arguments.getShortStatistic()) {
            programArguments.setStatisticType(StatisticType.SHORT);
        } else {
            programArguments.setStatisticType(StatisticType.NONE);
        }

        if (next != null) {
            next.process(arguments, programArguments);
        }
    }
}

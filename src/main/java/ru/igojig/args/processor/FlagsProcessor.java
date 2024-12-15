package ru.igojig.args.processor;

import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;
import ru.igojig.args.StatisticType;

public class FlagsProcessor extends AbstractProcessor{
    @Override
    public void process(Arguments arguments, EffectiveParameters effectiveParameters) {

        effectiveParameters.setAppend(arguments.getAppend());

        // если указаны два флага, то тогда полная статистика
        if (arguments.getFullStatistic()) {
            effectiveParameters.setStatisticType(StatisticType.FULL);
        } else if (arguments.getShortStatistic()) {
            effectiveParameters.setStatisticType(StatisticType.SHORT);
        } else {
            effectiveParameters.setStatisticType(StatisticType.NONE);
        }

    }
}

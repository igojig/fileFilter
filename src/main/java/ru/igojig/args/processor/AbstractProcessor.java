package ru.igojig.args.processor;

import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;

public abstract class AbstractProcessor {
    protected AbstractProcessor next;
    public abstract void process(Arguments arguments, EffectiveParameters effectiveParameters);
}

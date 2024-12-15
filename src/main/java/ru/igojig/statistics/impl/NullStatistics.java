package ru.igojig.statistics.impl;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.Statistics;

public class NullStatistics extends Statistics {
    @Override
    public void accumulate(ReadedObject readedObject) {
        //do nothing
    }

    @Override
    public void show() {
        //do nothing
    }
}

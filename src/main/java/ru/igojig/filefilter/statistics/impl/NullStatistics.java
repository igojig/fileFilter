package ru.igojig.filefilter.statistics.impl;

import ru.igojig.filefilter.statistics.Statistics;
import ru.igojig.filefilter.system.ReadedObject;

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

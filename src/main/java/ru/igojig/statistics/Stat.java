package ru.igojig.statistics;

import ru.igojig.ReadedObject;
import ru.igojig.statistics.data.BaseStatData;

public abstract class Stat {

    public abstract BaseStatData getStat();

    public abstract void accumulate(ReadedObject readedObject);

    public abstract void showShortStat();
    public abstract void showLongStat();
}

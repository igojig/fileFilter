package ru.igojig.statistics;

import lombok.Data;
import ru.igojig.ReadedObject;
import ru.igojig.statistics.data.ShortStatData;

@Data

public abstract class Statistics {
    protected boolean isUsed;

    public abstract void accumulate(ReadedObject readedObject);

    public abstract void show();
}

package ru.igojig.filefilter.statistics;

import lombok.Data;
import ru.igojig.filefilter.system.ReadedObject;

@Data

public abstract class Statistics {
    protected boolean isUsed;

    public abstract void accumulate(ReadedObject readedObject);

    public abstract void show();
}

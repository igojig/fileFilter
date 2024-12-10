package ru.igojig.statistics;

import ru.igojig.statistics.impl.FloatsStat;
import ru.igojig.statistics.impl.IntegersStat;
import ru.igojig.statistics.impl.StringsStat;
import ru.igojig.system.Floats;
import ru.igojig.system.Integers;
import ru.igojig.system.Strings;

import java.util.HashMap;
import java.util.Map;

public class StatFactory {

    private static final Map<Class<?>, Stat> statMap = new HashMap<>();

    static {
        statMap.put(Floats.class, new FloatsStat());
        statMap.put(Integers.class, new IntegersStat());
        statMap.put(Strings.class, new StringsStat());
    }

    public static Stat getStat(Class<?> aClass) {
        return statMap.get(aClass);
    }

}

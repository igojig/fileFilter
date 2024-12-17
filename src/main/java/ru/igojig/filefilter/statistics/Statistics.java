package ru.igojig.filefilter.statistics;

import lombok.Data;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Родительский класс для сбора статистики
 */
@Data
public abstract class Statistics {
    /**
     * Количество записанных данных
     */
    protected Long count = 0L;

    /**
     * Метод собирает статистику.
     * @param readedObject сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    public abstract void accumulate(ReadedObject readedObject);

    /**
     * Метод выводит собранную статистику
     */
    public abstract void show();

    protected void incrementCount() {
        ++count;
    }
}

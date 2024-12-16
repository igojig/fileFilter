package ru.igojig.filefilter.converter;

import lombok.Setter;
import ru.igojig.filefilter.system.ReadedObject;

/**
 * Родительский класс для конвертеров прочитанной строки в нужный тип данных
 */
@Setter
abstract class AbstractConvertor {
    protected AbstractConvertor next;

    /**
     * Метод должен попытаться преобразовать строку в свой тип данных. При неудаче должен передать вызов далее.
     * @param str прочитанная из файлов строка
     * @return сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     */
    public abstract ReadedObject convert(String str);
}

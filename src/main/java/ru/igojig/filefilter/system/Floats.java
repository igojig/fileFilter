package ru.igojig.filefilter.system;

/**
 * Маркерный интерфейс.
 * Предназначен для отметки что данные в строке являются вещественным типом данных в классе {@link ru.igojig.filefilter.system.ReadedObject}
 */
public interface Floats extends DataType{
    /**
     * Имя файла для вещественных типов данных
     */
    String filename="floats.txt";
}

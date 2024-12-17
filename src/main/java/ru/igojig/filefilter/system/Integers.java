package ru.igojig.filefilter.system;

/**
 * Маркерный интерфейс.
 * Предназначен для отметки что данные в строке являются целочисленным типом данных в классе {@link ru.igojig.filefilter.system.ReadedObject}
 */
public interface Integers extends DataType{
    /**
     * Имя файла для целочисленных типов данных
     */
    String filename="integers.txt";
}

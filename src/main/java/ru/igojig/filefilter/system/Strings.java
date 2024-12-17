package ru.igojig.filefilter.system;

/**
 * Маркерный интерфейс.
 * Предназначен для отметки что данные в строке являются строковым типом данных в классе {@link ru.igojig.filefilter.system.ReadedObject}
 */
public interface Strings extends DataType {
    /**
     * Имя файла для строковых типов данных
     */
    String filename="strings.txt";
}

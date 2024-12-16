package ru.igojig.filefilter.args;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Имена по умолчанию для выходных файлов
 */
@Getter
@AllArgsConstructor
public enum OutputFilenames {
    /**
     * Имя файла для записи целочисленных данных
     */
    INTEGERS("integers.txt"),

    /**
     * Имя файла для записи вещественных чисел
     */
    FLOATS("floats.txt"),

    /**
     * Имя файла для записи строковых данных
     */
    STRINGS("strings.txt");

    private final String fileName;
}

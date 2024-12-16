package ru.igojig.filefilter.args;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Значения по умолчанию
 */
@Getter
@AllArgsConstructor
public enum DefaultValues {
    /**
     * Префикс по умолчанию
     */
    DEFAULT_PREFIX(""),
    /**
     * Путь для записи файлов по умолчанию - текущая директория
     */
    DEFAULT_PATH(".");

    private final String defaultValue;
}

package ru.igojig.filefilter.args;

import ru.igojig.filefilter.argsprocessor.ArgsProcessor;

/**
 * Тип сбора статистики.
 * Формируется после обработки аргументов командной строки {@link ArgsProcessor}
 */
public enum StatisticType {
    NONE,
    /**
     * Если был установлен флаг "-s" {@link Arguments#shortStatistic}
     */
    SHORT,
    /**
     * Если был установлен флаг "-f" {@link Arguments#fullStatistic}
     */
    FULL
}

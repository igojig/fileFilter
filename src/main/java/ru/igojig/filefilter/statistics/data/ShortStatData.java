package ru.igojig.filefilter.statistics.data;

import lombok.Data;

/**
 * Краткая статистика.
 * Содержит только количество записанных данных
 * Остальные виды статистики должны наследоваться от этого класса
 */
@Data
public class ShortStatData {
    protected Long count = 0L;

    public void incrementCount() {
        ++count;
    }
}

package ru.igojig.filefilter.statistics.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Полная статистика для вещественных типов данных
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FloatsFullStatData extends ShortStatData {
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum = BigDecimal.ZERO;
}

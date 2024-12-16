package ru.igojig.filefilter.statistics.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
/**
 * Полная статистика для целочисленных типов данных
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IntegersFullStatData extends ShortStatData {
    private BigInteger min;
    private BigInteger max;
    private BigInteger sum = BigInteger.ZERO;
}

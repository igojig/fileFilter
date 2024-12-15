package ru.igojig.statistics.data;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;

@EqualsAndHashCode(callSuper = true)
@Data
public class FloatsFullStatData extends ShortStatData {
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum=BigDecimal.ZERO;
}

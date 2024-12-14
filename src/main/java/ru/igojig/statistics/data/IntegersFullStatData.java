package ru.igojig.statistics.data;

import lombok.*;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
public class IntegersFullStatData extends ShortStatData {
    private BigInteger min;
    private BigInteger max;
    private BigInteger sum=BigInteger.ZERO;
    private BigInteger average=BigInteger.ZERO;
}

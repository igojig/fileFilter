package ru.igojig.statistics.data;

import lombok.*;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegersStatData extends BaseStatData {
    private BigInteger min=BigInteger.valueOf(Long.MAX_VALUE);
    private BigInteger max=BigInteger.ZERO;
    private BigInteger sum=BigInteger.ZERO;
    private BigInteger average=BigInteger.ZERO;
}

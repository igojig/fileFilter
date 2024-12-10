package ru.igojig.statistics.data;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloatsStatData extends BaseStatData {
    private BigDecimal min=BigDecimal.valueOf(Long.MAX_VALUE);
    private BigDecimal max=BigDecimal.ZERO;
    private BigDecimal sum=BigDecimal.ZERO;
    private BigDecimal average=BigDecimal.ZERO;
}

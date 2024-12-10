package ru.igojig.statistics.data;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StringsStatData extends BaseStatData {
    private Long minLength=Long.MAX_VALUE;
    private Long maxLength=0L;
}

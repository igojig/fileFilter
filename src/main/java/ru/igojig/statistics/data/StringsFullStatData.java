package ru.igojig.statistics.data;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringsFullStatData extends ShortStatData {
    private Integer  minLength;
    private Integer maxLength;
}

package ru.igojig.filefilter.statistics.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * Полная статистика для строковых типов данных
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StringsFullStatData extends ShortStatData {
    private Integer minLength;
    private Integer maxLength;
}

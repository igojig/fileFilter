package ru.igojig.statistics.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseStatData {
    protected Long count=0L;

    public void incrementCount(){
        ++count;
    }
}

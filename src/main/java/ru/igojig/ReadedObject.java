package ru.igojig;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadedObject {
    private final String value;
    private final Class<?> type;
}

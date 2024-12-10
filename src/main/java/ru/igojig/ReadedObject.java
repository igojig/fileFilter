package ru.igojig;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadedObject {
    private final String stringValue;
    private final Class<?> type;
    // object representation
    private final Object obj;
}

package ru.igojig.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum DefaultValues {
    DEFAULT_PREFIX(""),
    DEFAULT_PATH(".");

    private final String defaultValue;
}

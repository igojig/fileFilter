package ru.igojig.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OutputFilenames {
    INTEGERS("integers.txt"),
    FLOATS("floats.txt"),
    STRINGS("strings.txt");

    private final String fileName;
}

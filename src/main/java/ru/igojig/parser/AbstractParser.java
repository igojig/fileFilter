package ru.igojig.parser;

import lombok.Setter;
import ru.igojig.ReadedObject;

@Setter
 abstract class AbstractParser {
    protected AbstractParser next;
    public abstract ReadedObject parse(String str);
}

package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Strings;

class StringsParser extends AbstractParser {
    @Override
    public ReadedObject parse(String str) {
        return ReadedObject.builder()
                .stringValue(str)
                .obj(str)
                .type(Strings.class)
                .build();
    }
}

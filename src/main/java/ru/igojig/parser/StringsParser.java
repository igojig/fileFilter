package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Strings;

//final in chain
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

package ru.igojig.converter;

import ru.igojig.ReadedObject;
import ru.igojig.system.Strings;

//final in chain
class StringsConvertor extends AbstractConvertor {
    @Override
    public ReadedObject convert(String str) {
        return ReadedObject.builder()
                .stringValue(str)
                .obj(str)
                .type(Strings.class)
                .build();
    }
}

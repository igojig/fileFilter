package ru.igojig.converter;

import ru.igojig.ReadedObject;
import ru.igojig.system.Floats;

import java.math.BigDecimal;

// second in chain
class FloatsConvertor extends AbstractConvertor {
    @Override
    public ReadedObject convert(String str) {
        try {
            BigDecimal val = new BigDecimal(str);
            return ReadedObject.builder()
                    .stringValue(str)
                    .type(Floats.class)
                    .obj(val)
                    .build();
        } catch (NumberFormatException e) {
            return next.convert(str);
        }
    }
}

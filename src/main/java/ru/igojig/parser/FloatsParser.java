package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Floats;

import java.math.BigDecimal;

// second in chain
class FloatsParser extends AbstractParser {
    @Override
    public ReadedObject parse(String str) {
        try {
            BigDecimal val = new BigDecimal(str);
            return ReadedObject.builder()
                    .stringValue(str)
                    .type(Floats.class)
                    .obj(val)
                    .build();
        } catch (NumberFormatException e) {
            return next.parse(str);
        }
    }
}

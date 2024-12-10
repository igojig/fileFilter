package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Integers;

import java.math.BigInteger;

// First in chain
 class IntegersParser extends AbstractParser {
    @Override
    public ReadedObject parse(String str) {
        try {
              BigInteger val=new BigInteger(str);
              return ReadedObject.builder()
                      .stringValue(str)
                      .type(Integers.class)
                      .obj(val)
                      .build();
        } catch (NumberFormatException e) {
            return next.parse(str);
        }
    }
}

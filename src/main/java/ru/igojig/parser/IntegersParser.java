package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Integers;

import java.math.BigInteger;

 class IntegersParser extends AbstractParser {
    @Override
    public ReadedObject parse(String str) {
        try {
              new BigInteger(str);
              return ReadedObject.builder().value(str).type(Integers.class).build();
        } catch (NumberFormatException e) {
            return next.parse(str);
        }
    }
}

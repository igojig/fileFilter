package ru.igojig.converter;

import ru.igojig.ReadedObject;
import ru.igojig.system.Integers;

import java.math.BigInteger;

// First in chain
 class IntegersConvertor extends AbstractConvertor {
    @Override
    public ReadedObject convert(String str) {
        try {
              BigInteger val=new BigInteger(str);
              return ReadedObject.builder()
                      .stringValue(str)
                      .type(Integers.class)
                      .obj(val)
                      .build();
        } catch (NumberFormatException e) {
            return next.convert(str);
        }
    }
}

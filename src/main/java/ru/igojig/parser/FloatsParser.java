package ru.igojig.parser;

import ru.igojig.ReadedObject;
import ru.igojig.system.Floats;

class FloatsParser extends AbstractParser {
    @Override
    public ReadedObject parse(String str) {
        try {
              Double.valueOf(str);
              return ReadedObject.builder().value(str).type(Floats.class).build();
        } catch (NumberFormatException e) {
            return next.parse(str);
        }
    }
}

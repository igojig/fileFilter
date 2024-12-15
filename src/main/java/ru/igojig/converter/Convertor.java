package ru.igojig.converter;

import ru.igojig.ReadedObject;

public class Convertor {

    private final IntegersConvertor integersParser = new IntegersConvertor();
    private final FloatsConvertor floatsParser = new FloatsConvertor();
    private final StringsConvertor stringsParser = new StringsConvertor();

    {
        integersParser.setNext(floatsParser);
        floatsParser.setNext(stringsParser);
    }

    public ReadedObject convert(String str) {
        return integersParser.convert(str);
    }
}

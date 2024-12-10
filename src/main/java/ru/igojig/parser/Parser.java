package ru.igojig.parser;

import ru.igojig.ReadedObject;

public class Parser {

    private final IntegersParser integersParser = new IntegersParser();
    private final FloatsParser floatsParser = new FloatsParser();
    private final StringsParser stringsParser = new StringsParser();

    {
        integersParser.setNext(floatsParser);
        floatsParser.setNext(stringsParser);
    }

    public ReadedObject parse(String str) {
        return integersParser.parse(str);
    }
}

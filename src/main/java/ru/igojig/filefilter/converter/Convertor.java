package ru.igojig.filefilter.converter;

import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.system.Strings;

/**
 * Стартовый класс в цепочке конвертеров
 */
public class Convertor {

    private final static IntegersConvertor integersParser = new IntegersConvertor();
    private final static FloatsConvertor floatsParser = new FloatsConvertor();
    private final static StringsConvertor stringsParser = new StringsConvertor();

   static  {
        integersParser.setNext(floatsParser);
        floatsParser.setNext(stringsParser);
    }

    /**
     * Метод запускает цепочку конвертеров. </br>
     * Преобразование работает так:</br>
     *    целочисленный тип -> вещественный тип -> строковый тип</br>
     * При удачном преобразовании должен установить нужный класс в поле {@link ReadedObject#type}:
     *  {@link Integers} - для целочисленных данных</br>
     *  {@link Floats} - для вещественных данных</br>
     *  {@link Strings} - для строковых данных</br>
     * @param str прочитанная из файлов строка
     * @return сконвертированная строка в нужный тип данных в виде объекта {@link ReadedObject}
     */
    public static ReadedObject convert(String str) {
        return integersParser.convert(str);
    }
}

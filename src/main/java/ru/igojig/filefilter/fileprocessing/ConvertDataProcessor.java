package ru.igojig.filefilter.fileprocessing;

import ru.igojig.filefilter.converter.Convertor;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.system.ReadedObject;

public class ConvertDataProcessor extends AbstractDataProcessor{
    /**
     * Метод конвертирует строку в нужный тип данных, вызывая конверторы {@link Convertor#convert(String)}
     * и передает данные следующему обработчику
     * @param obj прочитанная из файла строка (здесь она будет именно строкой)
     * @throws FileFilterIOException при ошибке пробрасывает исключение
     */
    @Override
    public void processing(Object obj) throws FileFilterIOException {
        String line=(String)obj;
        ReadedObject readedObject= Convertor.convert(line);
        if(next!=null){
            next.processing(readedObject);
        }

    }
}

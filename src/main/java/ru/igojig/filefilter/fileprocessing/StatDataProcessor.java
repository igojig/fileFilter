package ru.igojig.filefilter.fileprocessing;

import lombok.RequiredArgsConstructor;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.statistics.StatisticFactory;
import ru.igojig.filefilter.system.ReadedObject;

@RequiredArgsConstructor
public class StatDataProcessor extends AbstractDataProcessor {

    private final ProgramArguments programArguments;

    /**
     * Собирает статистику в зависимости от типа данных
     * @param obj сконвертированная строка
     * @throws FileFilterIOException при ошибке пробрасывает исключение
     */
    @Override
    public void processing(Object obj) throws FileFilterIOException {

        ReadedObject readedObject = (ReadedObject) obj;
        StatisticFactory.getStat(readedObject.getType(), programArguments.getStatisticType()).accumulate(readedObject);
        if (next != null) {
            next.processing(obj);
        }
    }
}

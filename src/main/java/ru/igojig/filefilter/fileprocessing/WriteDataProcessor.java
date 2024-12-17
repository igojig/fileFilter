package ru.igojig.filefilter.fileprocessing;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.system.ReadedObject;
import ru.igojig.filefilter.writers.AbstractWriter;
import ru.igojig.filefilter.writers.WriterFactory;

@Log4j2
public class WriteDataProcessor extends AbstractDataProcessor {
    private final ProgramArguments programArguments;

    /**
     * Конструктор класса. Инициализирует фабрику {@link WriterFactory} именами файлов
     * для соответствующих типов данных.
     * Открывает файлы для записи с нужными параметрами, при ошибке программа завершается.
     *
     * @param programArguments обработанные параметры командной строки {@link ru.igojig.filefilter.args.ProgramArguments}
     * @throws FileFilterIOException если произошла ошибка открытия потоков на запись, пробрасывает исключение
     */
    public WriteDataProcessor(ProgramArguments programArguments) throws FileFilterIOException {
        this.programArguments = programArguments;
        WriterFactory.initFactory(programArguments.getOutputNamesToClassMap());
        WriterFactory.openWriters(programArguments.getWriteOptions());
    }

    /**
     * Метод записывает строку в нужный файл в зависимости от типа данных.
     * @param obj сконвертированная в нужный тип данных строка в виде объекта {@link ReadedObject}
     * @throws FileFilterIOException при ошибке записи пробрасывается исключение.
     */
    @Override
    public void processing(Object obj) throws FileFilterIOException {
        ReadedObject readedObject = (ReadedObject) obj;
        AbstractWriter abstractWriter = WriterFactory.getWriter(readedObject.getType());
        abstractWriter.write(readedObject);
        if (next != null) {
            next.processing(readedObject);
        }
    }
}

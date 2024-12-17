package ru.igojig.filefilter.writers;

import ru.igojig.filefilter.exceptions.FileFilterIOException;
import ru.igojig.filefilter.fileprocessing.ReadDataProcessor;
import ru.igojig.filefilter.system.DataType;
import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.Strings;
import ru.igojig.filefilter.writers.impl.FloatsWriter;
import ru.igojig.filefilter.writers.impl.IntegersWriter;
import ru.igojig.filefilter.writers.impl.StringsWriter;
import ru.igojig.filefilter.writers.impl.UnsupportedWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Фабрика классов для записи данных
 */
public class WriterFactory {
    /**
     * Карта соответствий типов данных и классов для записи
     */
    private final static Map<Class<? extends DataType>, AbstractWriter> writersMap = new HashMap<>();

    /**
     * Инициализация фабрики путем сопоставления типа данных, класса для записи и имени файла для этого класса
     * @param filesMap Карта соответствия типа данных (текстовый {@link Strings},
     *      целочисленный {@link Integers},
     *      вещественные числа {@link  Floats}) и имен выходных файлов.
     *      {@link ReadDataProcessor}
     */
    public static void initFactory(Map<Class<? extends DataType>, Path> filesMap) {
        writersMap.put(Floats.class, new FloatsWriter(filesMap.get(Floats.class)));
        writersMap.put(Integers.class, new IntegersWriter(filesMap.get(Integers.class)));
        writersMap.put(Strings.class, new StringsWriter(filesMap.get(Strings.class)));
    }

    /**
     * Возвращает класс для записи
     * @param aClass тип данных
     * @return класс для записи
     */
    public static AbstractWriter getWriter(Class<? extends DataType> aClass) {
        return writersMap.getOrDefault(aClass, new UnsupportedWriter());
    }

    /**
     * Закрывает все потоки открытые для записи
     */
    public static void closeAll() {
        for (AbstractWriter writer : writersMap.values()) {
            writer.close();
        }
    }

    /**
     * Возвращает список файлов, которые были действительно использованы для записи,
     * т.е. файлы, в которые хоть раз произошла запись.
     * @return
     */
    public static List<Path> getUsedPaths() {
        return writersMap.values().stream()
                .filter(AbstractWriter::isUsed)
                .map(AbstractWriter::getPath)
                .toList();
    }

    public static void openWriters(StandardOpenOption... writeOptions) throws FileFilterIOException {
        for (AbstractWriter writer : writersMap.values()) {
            try {
                writer.open(writeOptions);
            } catch (IOException e) {
                throw new FileFilterIOException(writer, e.getMessage());
            }
        }
    }
}

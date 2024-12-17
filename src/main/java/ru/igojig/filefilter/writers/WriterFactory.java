package ru.igojig.filefilter.writers;

import ru.igojig.filefilter.exceptions.WriterOpenException;
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

public class WriterFactory {
    private final Map<Class<? extends DataType>, AbstractWriter> writersMap = new HashMap<>();

    public void initFactory(Map<Class<? extends DataType>, Path> filesMap) {
        writersMap.put(Floats.class, new FloatsWriter(filesMap.get(Floats.class)));
        writersMap.put(Integers.class, new IntegersWriter(filesMap.get(Integers.class)));
        writersMap.put(Strings.class, new StringsWriter(filesMap.get(Strings.class)));
    }

    public AbstractWriter getWriter(Class<? extends DataType> aClass) {
        return writersMap.getOrDefault(aClass, new UnsupportedWriter());
    }

    public void closeAll() {
        for (AbstractWriter writer : writersMap.values()) {
            writer.close();
        }
    }

    public List<Path> getUsedPaths() {
        return writersMap.values().stream()
                .filter(AbstractWriter::isUsed)
                .map(AbstractWriter::getPath)
                .toList();
    }

    public void openWriters(StandardOpenOption... writeOptions) throws WriterOpenException {
        for (AbstractWriter writer : writersMap.values()) {
            try {
                writer.open(writeOptions);
            } catch (IOException e) {
                throw new WriterOpenException(writer, e.getMessage());
            }
        }
    }
}

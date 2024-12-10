package ru.igojig.writers;

import ru.igojig.args.EffectiveParameters;
import ru.igojig.exceptions.WriterOpenException;
import ru.igojig.system.Floats;
import ru.igojig.system.Integers;
import ru.igojig.system.Strings;
import ru.igojig.writers.impl.FloatsWriter;
import ru.igojig.writers.impl.IntegersWriter;
import ru.igojig.writers.impl.StringsWriter;
import ru.igojig.writers.impl.UnsupportedWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriterFactory {
    private final Map<Class<?>, DataWriter> writersMap = new HashMap<>();


    public void initFactory(EffectiveParameters effectiveParameters) {
        writersMap.put(Floats.class, new FloatsWriter(effectiveParameters.getOutputFloatsPath()));
        writersMap.put(Integers.class, new IntegersWriter(effectiveParameters.getOutputIntegesrPath()));
        writersMap.put(Strings.class, new StringsWriter(effectiveParameters.getOutputStringPath()));

    }

    public DataWriter getWriter(Class<?> aClass) {
        return writersMap.getOrDefault(aClass, new UnsupportedWriter());
    }

    public void openFiles(List<Path> paths) {


    }


    public void closeAll() {
        for (DataWriter writer : writersMap.values()) {
            ((BaseWriter) writer).close();
        }
    }


    public void openWriters() throws WriterOpenException {
        for (DataWriter writer : writersMap.values()) {
            try {
                ((BaseWriter) writer).open();
            } catch (IOException e) {
                throw new WriterOpenException(((BaseWriter) writer), e.getMessage());
            }
        }
    }
}

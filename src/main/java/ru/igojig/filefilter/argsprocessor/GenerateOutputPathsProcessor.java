package ru.igojig.filefilter.argsprocessor;

import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.Strings;

import java.nio.file.Path;

/**
 * Формирует имена выходных файлов с учетом префикса (флага "-p" {@link Arguments#outputPrefix})
 * и пути (флага "-o" {@link Arguments#outputPrefix})
 */
public class GenerateOutputPathsProcessor extends AbstractProcessor {
    /**
     * Метод для обработки.
     * @param arguments аргументы командной строки, входящие данные. {@link Arguments}
     * @param programArguments обработанные аргументы для программы, исходящие данные {@link ProgramArguments}
     */
    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {

        String validOutputPath = arguments.getOutputPath();
        String validPrefix = arguments.getOutputPrefix();

        programArguments.getOutputNamesToClassMap().put(Integers.class,
                Path.of(validOutputPath, validPrefix + Integers.filename)
                        .normalize()
                        .toAbsolutePath());
        programArguments.getOutputNamesToClassMap().put(Floats.class,
                Path.of(validOutputPath, validPrefix + Floats.filename)
                        .normalize()
                        .toAbsolutePath());
        programArguments.getOutputNamesToClassMap().put(Strings.class,
                Path.of(validOutputPath, validPrefix + Strings.filename)
                        .normalize()
                        .toAbsolutePath());

        if (next != null) {
            next.process(arguments, programArguments);
        }

    }
}

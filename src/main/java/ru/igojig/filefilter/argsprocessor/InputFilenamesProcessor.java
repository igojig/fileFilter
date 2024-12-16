package ru.igojig.filefilter.argsprocessor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Формирует из списка входных файлов {@link Arguments#inputFileNames}
 * список файлов в виде {@code Path}. Если файл не существует или не доступен для чтения,
 * то файл исключается из обработки. Если ни один файл не найден, то программа завершает работу.
 */
@Log4j2
public class InputFilenamesProcessor extends AbstractProcessor {

    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {

        List<String> inputs = arguments.getInputFileNames();
        List<Path> paths = new ArrayList<>();

        inputs.forEach(f -> toPath(f).ifPresent(paths::add));
        if (paths.isEmpty()) {
            log.fatal("No input files can be proceeded. Exit.");
            System.exit(1);
        }
        programArguments.setInputFilenames(paths);

        if (next != null) {
            next.process(arguments, programArguments);
        }
    }

    private Optional<Path> toPath(String strPath) {
        try {
            Path currentDir = Path.of(".");
            Path path = Path.of(strPath).normalize();
            path = currentDir.resolve(path).toRealPath();
            if(!Files.isReadable(path)){
                log.error("File {} is not readable. Exclude file from processing", path);
                return Optional.empty();
            }
            return Optional.of(path);
        } catch (RuntimeException | IOException e) {
            log.error("Invalid input filename. File {} will be excluded from processing", strPath);
            return Optional.empty();
        }
    }
}

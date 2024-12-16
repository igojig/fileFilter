package ru.igojig.filefilter.argsprocessor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.DefaultValues;
import ru.igojig.filefilter.args.ProgramArguments;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Валидирует путь для записи файлов (флаг "-o" {@link ru.igojig.filefilter.args.Arguments#outputPath})
 * Если путь невалидный то устанавливается путь по умолчанию - текущая директория {@link ru.igojig.filefilter.args.DefaultValues#DEFAULT_PATH}
 */
@Log4j2
public class OutputPathProcessor extends AbstractProcessor {
    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {

        String outputPath = arguments.getOutputPath();

        Optional<String> optional = checkPath(outputPath);

        if (optional.isEmpty()) {
            log.warn("Output path will set to default value");
            arguments.setOutputPath(DefaultValues.DEFAULT_PATH.getDefaultValue());
        }

        if (next != null) {
            next.process(arguments, programArguments);
        }
    }

    private Optional<String> checkPath(String strPath) {
        try {
            Path currentDir = Path.of(".");
            Path outputPath = Path.of(strPath).normalize();
            Files.createDirectories(outputPath);
            outputPath = currentDir.resolve(outputPath).toRealPath();

            Path tempFile = Files.createTempFile(outputPath, null, null);
            try {
                Files.delete(tempFile);
            } catch (IOException e) {
                log.warn("Error deleting temp file: {}", e.getMessage());
            }
            return Optional.of(strPath);
        } catch (InvalidPathException e) {
            log.error("Output path is invalid due to: {}", e.getMessage());
        } catch (AccessDeniedException e) {
            log.error("Access denied create path: " + strPath);
        } catch (IOException e) {
            log.error("Error creating output path {} due to {}", strPath, e.getMessage());
        }
        return Optional.empty();
    }
}

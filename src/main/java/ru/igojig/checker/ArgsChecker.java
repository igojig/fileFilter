package ru.igojig.checker;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

@Data
@Log4j2
public class ArgsChecker {
    public static final String REGEX_FILENAME_PATTERN = "^[A-Za-z0-9.]{1,255}$";

    public static CheckStatus check(Arguments arguments) {
        CheckStatus checkStatus = new CheckStatus();

        if (!checkInputFilenames(arguments.getInputFileNames())) {
            checkStatus.setFixInputFilenames(true);
        }
        if (!checkOutputPrefix(arguments.getOutputPrefix())) {
            checkStatus.setFixOutputPrefix(true);
        }
        if (!checkOutputPath(arguments.getOutputPath())) {
            checkStatus.setFixOutputPath(true);
        }
        return checkStatus;
    }

    private static boolean  checkInputFilenames(List<String> fileList) {
        log.info("logger");
        if (fileList == null || fileList.isEmpty()) {
//            log.warn("Input files not present");
            return false;
        }
        return true;
    }

    private static boolean checkOutputPrefix(String prefix) {

        //            log.warn("Illegal prefix: {}", prefix);
        return prefix != null && prefix.matches(REGEX_FILENAME_PATTERN);
    }

    private static boolean checkOutputPath(String pathStr) {

        if (pathStr == null || pathStr.isBlank()) {
//            log.warn("Output path not valid");
            return false;
        }

        try {
            Path currentPath = Path.of(".");
            Path outputPath = Path.of(pathStr);
            outputPath = currentPath.resolve(outputPath.normalize());
            String canonicalPath;
            canonicalPath = outputPath.toFile().getCanonicalPath();
//            log.trace("Canonical output path is: {}", canonicalPath);
            return true;
        } catch (InvalidPathException|IOException e) {
//            log.warn("Output path is not valid: {}", output);
            return false;
        }
    }

}

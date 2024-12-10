package ru.igojig.checker;

import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.DefaultValues;
import ru.igojig.args.EffectiveParameters;
import ru.igojig.system.OutputFilenames;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
public class ValidateAndFix {

    public static final String REGEX_FILENAME_PATTERN = "^[\\sA-Za-z0-9._]{1,255}$";

    public static void validateAndFix(Arguments arguments, EffectiveParameters effectiveParameters) {
        List<String> inputFileNames = arguments.getInputFileNames();
        List<Path> paths = new ArrayList<>();

        inputFileNames.forEach(f->toPath(f).ifPresent(paths::add));
        if(paths.isEmpty()){
            log.fatal("No input files can be proceeded. Exit.");
            System.exit(1);
        }
        effectiveParameters.setInputFilenames(paths);

        String prefix= arguments.getOutputPrefix();

        if(!validatePrefix(prefix)){
            prefix=fixPrefix(prefix);
        }

        String outputPath= arguments.getOutputPath();
        String validOutputPath = validateAndFixOutputPath(outputPath);

        String stringOutputFilename= OutputFilenames.STRINGS.getFileName();
        String floatsOutputFilename = OutputFilenames.FLOATS.getFileName();
        String integersOutputFilename =  OutputFilenames.INTEGERS.getFileName();

        effectiveParameters.setOutputStringPath(Path.of(validOutputPath , prefix +stringOutputFilename).normalize().toAbsolutePath());
        effectiveParameters.setOutputFloatsPath(Path.of(validOutputPath , prefix +floatsOutputFilename).normalize().toAbsolutePath());
        effectiveParameters.setOutputIntegesrPath(Path.of(validOutputPath , prefix +integersOutputFilename).normalize().toAbsolutePath());

        effectiveParameters.setFullStatistic(arguments.getFullStatistic());
        effectiveParameters.setShortStatistic(arguments.getShortStatistic());
        effectiveParameters.setAppend(arguments.getAppend());


    }

    private static Optional<Path> toPath(String strPath) {
        try {
            Path currentDir = Path.of(".");
            Path path = Path.of(strPath).normalize();
            path = currentDir.resolve(path).toRealPath();
            return Optional.of(path);
        } catch (RuntimeException | IOException e) {
            log.error("Invalid input filename. File {} will be excluded from analyzing", strPath);
            return Optional.empty();
        }
    }

    private static boolean validatePrefix(String prefix){
        if(prefix==null){
            return false;
        }
     if(prefix.isEmpty()){
         return true;
     }

        if(!prefix.matches(REGEX_FILENAME_PATTERN)){
            log.error("Prefix contains invalid characters: {}", prefix);
            return false;
        }

        return true;
    }

    private static String fixPrefix(String prefix){
        log.warn("Prefix will set to default");
        return DefaultValues.DEFAULT_PREFIX.getDefaultValue();
    }

    private static String validateAndFixOutputPath(String path){
        try {
            Path currentDir=Path.of(".");
            Path outputPath=Path.of(path).normalize();
            outputPath = currentDir.resolve(outputPath);
            return path;
        } catch (RuntimeException e){
            log.error("Output path is invalid due to: {} \noutput path will set to default: {}",
                    e.getMessage(), DefaultValues.DEFAULT_PATH.getDefaultValue());
            return DefaultValues.DEFAULT_PATH.getDefaultValue();
        }
    }

//    private static Optional<Path> toPrefix(String prefix){
////        try {
////            Path path=Path.
////        }
//    }
}

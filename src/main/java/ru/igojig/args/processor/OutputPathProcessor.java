package ru.igojig.args.processor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.DefaultValues;
import ru.igojig.args.EffectiveParameters;

import java.nio.file.Path;
import java.util.Optional;

@Log4j2
public class OutputPathProcessor extends AbstractProcessor{
    @Override
    public void process(Arguments arguments, EffectiveParameters effectiveParameters) {

        String outputPath= arguments.getOutputPath();

        Optional<String> optional=validateAndFixOutputPath(outputPath);

        if(optional.isEmpty()){
            log.warn("Output path will set to default value");
            arguments.setOutputPath(DefaultValues.DEFAULT_PATH.getDefaultValue());
        }

        if(next!=null){
            next.process(arguments, effectiveParameters);
        }


    }

    private Optional<String> validateAndFixOutputPath(String path) {
        try {
            Path currentDir = Path.of(".");
            Path outputPath = Path.of(path).normalize();
            outputPath = currentDir.resolve(outputPath).toAbsolutePath();
            return Optional.of(path);
        } catch (RuntimeException e) {
            log.error("Output path is invalid due to: {}",
                    e.getMessage());
            return Optional.empty();
        }
    }
}

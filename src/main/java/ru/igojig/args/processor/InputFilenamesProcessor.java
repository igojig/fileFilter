package ru.igojig.args.processor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class InputFilenamesProcessor extends AbstractProcessor {

    @Override
    public void process(Arguments arguments, EffectiveParameters effectiveParameters) {


        List<String> inputs=arguments.getInputFileNames();
        List<Path> paths = new ArrayList<>();

        inputs.forEach(f -> toPath(f).ifPresent(paths::add));
        if (paths.isEmpty()) {
            log.fatal("No input files can be proceeded. Exit.");
            System.exit(1);
        }
        effectiveParameters.setInputFilenames(paths);

        if(next!=null){
            next.process(arguments, effectiveParameters);
        }

    }


    private  Optional<Path> toPath(String strPath) {
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
}

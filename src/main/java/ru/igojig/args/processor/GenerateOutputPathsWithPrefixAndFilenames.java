package ru.igojig.args.processor;

import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;
import ru.igojig.args.OutputFilenames;

import java.nio.file.Path;

public class GenerateOutputPathsWithPrefixAndFilenames extends AbstractProcessor{
    @Override
    public void process(Arguments arguments, EffectiveParameters effectiveParameters) {
        String stringOutputFilename = OutputFilenames.STRINGS.getFileName();
        String floatsOutputFilename = OutputFilenames.FLOATS.getFileName();
        String integersOutputFilename = OutputFilenames.INTEGERS.getFileName();

        String validOutputPath= arguments.getOutputPath();
        String validPrefix= arguments.getOutputPrefix();

        effectiveParameters.setOutputStringPath(Path.of(validOutputPath, validPrefix + stringOutputFilename)
                .normalize()
                .toAbsolutePath());
        effectiveParameters.setOutputFloatsPath(Path.of(validOutputPath, validPrefix + floatsOutputFilename)
                .normalize()
                .toAbsolutePath());
        effectiveParameters.setOutputIntegesrPath(Path.of(validOutputPath, validPrefix + integersOutputFilename)
                .normalize()
                .toAbsolutePath());

        if(next!=null){
            next.process(arguments, effectiveParameters);
        }

    }
}

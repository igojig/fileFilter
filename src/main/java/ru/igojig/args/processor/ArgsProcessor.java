package ru.igojig.args.processor;

import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;

public class ArgsProcessor {
    private final InputFilenamesProcessor inputFilenamesProcessor=new InputFilenamesProcessor();
    private final OutputPathProcessor outputPathProcessor=new OutputPathProcessor();
    private final OutputPrefixProcessor outputPrefixProcessor=new OutputPrefixProcessor();
    private final GenerateOutputPathsWithPrefixAndFilenames generateOutputPathsWithPrefixAndFilenames =
            new GenerateOutputPathsWithPrefixAndFilenames();
    private final FlagsProcessor flagsProcessor=new FlagsProcessor();

    {
        inputFilenamesProcessor.next=outputPathProcessor;
        outputPathProcessor.next=outputPrefixProcessor;
        outputPrefixProcessor.next=generateOutputPathsWithPrefixAndFilenames;
        generateOutputPathsWithPrefixAndFilenames.next=flagsProcessor;
    }


    public void process(Arguments arguments, EffectiveParameters effectiveParameters){
        inputFilenamesProcessor.process(arguments, effectiveParameters);
    }

}

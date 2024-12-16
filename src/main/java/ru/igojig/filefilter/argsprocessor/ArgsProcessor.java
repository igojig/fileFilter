package ru.igojig.filefilter.argsprocessor;

import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;


/**
 * Стартовый класс для цепочки обработчиков аргументов командной строки.
 * Порядок обработки имеет значение.
 */
public class ArgsProcessor {
    private final InputFilenamesProcessor inputFilenamesProcessor = new InputFilenamesProcessor();
    private final OutputPathProcessor outputPathProcessor = new OutputPathProcessor();
    private final OutputPrefixProcessor outputPrefixProcessor = new OutputPrefixProcessor();
    private final GenerateOutputPathsProcessor generateOutputPathsProcessor =
            new GenerateOutputPathsProcessor();
    private final AppendFlagProcessor appendFlagProcessor = new AppendFlagProcessor();
    private final StatisticsFlagProcessor statisticsFlagProcessor = new StatisticsFlagProcessor();

    {
        inputFilenamesProcessor.next = outputPathProcessor;
        outputPathProcessor.next = outputPrefixProcessor;
        outputPrefixProcessor.next = generateOutputPathsProcessor;
        generateOutputPathsProcessor.next = appendFlagProcessor;
        appendFlagProcessor.next = statisticsFlagProcessor;
    }

    /**
     * Метод для обработки.
     * @param arguments аргументы командной строки, входящие данные. {@link ru.igojig.filefilter.args.Arguments}
     * @param programArguments обработанные аргументы для программы, исходящие данные {@link ru.igojig.filefilter.args.ProgramArguments}
     */
    public void process(Arguments arguments, ProgramArguments programArguments) {
        inputFilenamesProcessor.process(arguments, programArguments);
    }

}

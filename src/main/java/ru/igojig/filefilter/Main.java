package ru.igojig.filefilter;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;
import ru.igojig.filefilter.argsprocessor.ArgsProcessor;
import ru.igojig.filefilter.processing.ProcessFiles;

import java.io.IOException;

@Log4j2
public class Main {
    public static void main(String[] args) throws IOException {
        log.trace("Start");
        args = new String[]{"-p", "prfx_xx", "in1.txt",  "in2.txt", "in3.txt", "-f",};
//        args = new String[]{"-o", "/*out", "-p", "pr\\ef_", "-a", "  "};
//        args = new String[]{"-p", "pr\\}ef_", "-a", "in1.t", "in2.txt", "in3.txt"};
//        args = new String[]{"--help"};

        Arguments commandLineArguments = new Arguments();

        // парсим аргументы командной строки
        JCommander jc = JCommander.newBuilder().addObject(commandLineArguments).build();
        try {
            jc.parse(args);
        } catch (ParameterException e) {
            log.error("Command line arguments error: {}", e.getMessage());
            e.usage();
        }
        // если указана команда '--help', то выводим помощь
        if(commandLineArguments.isHelp()){
            jc.usage();
            System.exit(0);
        }

        log.trace("Command line args: {}", commandLineArguments);
        ProgramArguments programArguments = new ProgramArguments();

        // преобразуем аргументы командной строки
        // в нужную нам форму
        ArgsProcessor.process(commandLineArguments, programArguments);

        log.trace(programArguments);

        // запускаем процесс обработки файлов
        ProcessFiles processFiles = new ProcessFiles(programArguments);
        processFiles.process();
        System.out.println("Done.");
        log.trace("End");
    }
}
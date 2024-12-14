package ru.igojig;


import com.beust.jcommander.JCommander;
import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.EffectiveParameters;
import ru.igojig.checker.ValidateAndFix;
import ru.igojig.processing.Process;

import java.io.IOException;
import java.nio.file.Path;

@Log4j2
public class Main {
    public static void main(String[] args) throws IOException {
        log.info("Start");
        args = new String[]{ "-a", "-s" , "tests.txt"};
//        args = new String[]{"-o", "/*out", "-p", "pr\\ef_", "-a", "  "};
//        args = new String[]{"-p", "pr\\}ef_", "-a", "in1.t", "in2.txt", "in3.txt"};

        Arguments commandLineArguments = new Arguments();

        JCommander jc = JCommander.newBuilder().addObject(commandLineArguments).build();
        jc.parse(args);

//        jc.usage();
        System.out.println(commandLineArguments);

        Path p;

        EffectiveParameters effectiveParameters=new EffectiveParameters();

        ValidateAndFix.validateAndFix(commandLineArguments, effectiveParameters);

        System.out.println(effectiveParameters);


        Process process=new Process(effectiveParameters);
        process.process();


//        DataReader dataReader=new DataReader();
//
//        dataReader.setFrom(commandLineArguments.getInputFileNames().get(0));
//        dataReader.read();


    }
}
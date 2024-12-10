package ru.igojig.checker;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.DefaultValues;

@Data
@Log4j2
public class Fixer {


    public  static void fix(Arguments arguments, CheckStatus checkStatus){
        if(checkStatus.isFixInputFilenames()){
            log.error("No input files given. Exit program");
            System.exit(1);
        }
        if(checkStatus.isFixOutputPrefix()){
            log.info("Set output prefix to default: {}", DefaultValues.DEFAULT_PREFIX.getDefaultValue());
            arguments.setOutputPrefix(DefaultValues.DEFAULT_PREFIX.getDefaultValue());
        }
        if(checkStatus.isFixOutputPath()){
            log.info("Set output path to current working directory");
            arguments.setOutputPath(DefaultValues.DEFAULT_PATH.getDefaultValue());
        }
    }

}

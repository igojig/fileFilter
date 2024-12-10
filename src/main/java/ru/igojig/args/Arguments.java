package ru.igojig.args;


import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@NoArgsConstructor
@Data
//@EffectiveParameters(parametersValidators = CustomValidator.class)
public class Arguments {


    @Parameter(description = "file1 [, file2, ...]")
    private List<String> inputFileNames=new ArrayList<>();

    @Parameter(names = "-p", arity = 1, description = "set output prefix")
    private String outputPrefix= DefaultValues.DEFAULT_PREFIX.getDefaultValue();

    @Parameter(names = "-o", arity = 1, description = "output path")
    private String outputPath= DefaultValues.DEFAULT_PATH.getDefaultValue();

    @Parameter(names = "-a", description = "append results")
    private Boolean append=false;

    @Parameter(names = "-s", description = "short statistics")
    private Boolean shortStatistic=false;

    @Parameter(names = "-f", description = "full statistics")
    private Boolean fullStatistic=false;

    @Parameter(names = "--help", help = true)
    private boolean help;

}

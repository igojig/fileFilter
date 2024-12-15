package ru.igojig.args;

import lombok.Data;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Data
public class EffectiveParameters {
   private List<Path> inputFilenames;

   // resolved outputPath , prefix, names
   private Path outputIntegesrPath;
   private Path outputFloatsPath;
   private Path outputStringPath;

   private Boolean append;
   private StatisticType statisticType;
}

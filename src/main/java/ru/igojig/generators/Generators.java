package ru.igojig.generators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Generators {
    public static void main(String[] args) {


        try (BufferedWriter bufferedWriter= Files.newBufferedWriter(Path.of("tests.txt"), StandardCharsets.UTF_8)){

            for(int i=0;i<10;i++){
                int rnd=ThreadLocalRandom.current().nextInt(3);
                //integers
                if(rnd==0){
                    long l = ThreadLocalRandom.current().nextLong();
                    bufferedWriter.write(String.valueOf(l));
                    bufferedWriter.newLine();
                }
                //floats
                if(rnd==1){
                    double v = ThreadLocalRandom.current().nextDouble(Double.MAX_VALUE);
                    bufferedWriter.write(String.valueOf(v));
                    bufferedWriter.newLine();
                }
                //strings
                if(rnd==2){
                    String uuid = UUID.randomUUID().toString();
                    bufferedWriter.write(uuid);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

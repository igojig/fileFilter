package ru.igojig.filefilter.generators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для генерации тестовых данных
 */
public class Generators {
    public static void main(String[] args) {


        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("tests.txt"), StandardCharsets.UTF_8)) {

            for (int i = 0; i < 500_000_000; i++) {
                int rnd = ThreadLocalRandom.current().nextInt(3);
                //integers
                if (rnd == 0) {
                    long l = ThreadLocalRandom.current().nextLong();
                    bufferedWriter.write(String.valueOf(l));
                    bufferedWriter.newLine();
                }
                //floats
                if (rnd == 1) {
                    double v = ThreadLocalRandom.current().nextDouble(Double.MAX_VALUE);
                    bufferedWriter.write(String.valueOf(v));
                    bufferedWriter.newLine();
                }
                //strings
                if (rnd == 2) {
                    String str = randomString();
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String randomString() {
        int len = ThreadLocalRandom.current().nextInt(1, 100_000);
        int leftLimit = 58;
        int rightLimit = 122;

        String string = ThreadLocalRandom.current()
                .ints(len, leftLimit, rightLimit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return string;

    }
}

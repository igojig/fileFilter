package ru.igojig.filefilter.args;


import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержит аргументы командной строки
 */
@Data
public class Arguments {

    /**
     * Список файлов для обработки
     */
    @Parameter(description = "file1 [, file2, ...]", order = 0)
    private List<String> inputFileNames = new ArrayList<>();

    /**
     * Префикс для выходных файлов {@link OutputFilenames},
     * значения по умолчанию {@link DefaultValues#DEFAULT_PREFIX}
     */
    @Parameter(names = "-p", arity = 1, description = "prefix for output files", defaultValueDescription = "no prefix", order = 1)
    private String outputPrefix = DefaultValues.DEFAULT_PREFIX.getDefaultValue();

    /**
     * Путь для записи файлов {@link OutputFilenames},
     * значения по умолчанию {@link DefaultValues#DEFAULT_PATH}
     */
    @Parameter(names = "-o", arity = 1, description = "output path", defaultValueDescription = "current folder", order = 2)
    private String outputPath = DefaultValues.DEFAULT_PATH.getDefaultValue();

    /**
     * Флаг для добавления результатов в существующие файлы,
     * по умолчанию файлы перезаписываются
     */
    @Parameter(names = "-a", description = "append results", defaultValueDescription = "false - overwrite files", order = 3)
    private Boolean append = false;

    /**
     * Флаг для сбора краткой статистики - содержит только
     * количество элементов записанных в исходящие файлы.
     */
    @Parameter(names = "-s", description = "show short statistics", defaultValueDescription = "do not show short statistic", order = 4)
    private Boolean shortStatistic = false;

    /**
     * Флаг для сбора полной статистики
     * Полная статистика для чисел
     * дополнительно содержит минимальное и максимальное значения, сумма и среднее.
     * Полная статистика для строк, помимо их количества, содержит также размер самой
     * короткой строки и самой длинной.
     */
    @Parameter(names = "-f", description = "show full statistic", defaultValueDescription = "do not show full statistic", order = 5)
    private Boolean fullStatistic = false;

    /**
     * Вывод помощи
     */
    @Parameter(names = "--help", help = true, description = "show help")
    private boolean help;

}

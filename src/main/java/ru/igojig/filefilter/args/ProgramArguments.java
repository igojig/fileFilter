package ru.igojig.filefilter.args;

import lombok.Data;
import ru.igojig.filefilter.argsprocessor.ArgsProcessor;
import ru.igojig.filefilter.system.DataType;
import ru.igojig.filefilter.system.Floats;
import ru.igojig.filefilter.system.Integers;
import ru.igojig.filefilter.system.Strings;

import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Параметры для программы, получаемые после обработки процессорами аргументов командной строки.
 * {@link ArgsProcessor}
 */
@Data
public class ProgramArguments {
    /**
     * Список файлов для обработки
     */
    private List<Path> inputFilenames;

    /**
     * Тип сбора статистики.
     * {@link StatisticType}
     */
    private StatisticType statisticType;

    /**
     * Параметры для записи файлов
     */
    private StandardOpenOption[] writeOptions;

    /**
     * Карта соответствия типа данных (текстовый {@link Strings},
     * целочисленный {@link Integers},
     * вещественные числа {@link  Floats}) и имен выходных файлов.
     * {@link ru.igojig.filefilter.argsprocessor.GenerateOutputPathsProcessor}
     */
    private Map<Class<? extends DataType>, Path> outputNamesToClassMap = new HashMap<>();

    /**
     * Сообщение при успешном завершении программы
     */
    private String doneMessage;
}

package ru.igojig.filefilter.argsprocessor;

import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.ProgramArguments;

/**
 * Родительский класс для обработчиков аргументов командной строки
 */
public abstract class AbstractProcessor {
    /**
     * Ссылка на следующий обработчик
     */
    protected AbstractProcessor next;

    /**
     * Метод для обработки.
     * @param arguments аргументы командной строки, входящие данные. {@link Arguments}
     * @param programArguments обработанные аргументы для программы, исходящие данные {@link ProgramArguments}
     */
    public abstract void process(Arguments arguments, ProgramArguments programArguments);
}

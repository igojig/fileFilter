package ru.igojig.filefilter.argsprocessor;

import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.MessageStrings;
import ru.igojig.filefilter.args.ProgramArguments;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик флага "-a" {@link ru.igojig.filefilter.args.Arguments#append}
 * Устанавливает параметры записи файлов {@link ProgramArguments#writeOptions}
 * и сообщение при успешном завершении программы {@link ru.igojig.filefilter.args.ProgramArguments#doneMessage}
 */
public class AppendFlagProcessor extends AbstractProcessor {
    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {

        List<StandardOpenOption> writeOptions = new ArrayList<>();

        if (arguments.getAppend()) {
            writeOptions.add(StandardOpenOption.WRITE);
            writeOptions.add(StandardOpenOption.CREATE);
            writeOptions.add(StandardOpenOption.APPEND);
        } else {
            writeOptions.add(StandardOpenOption.WRITE);
            writeOptions.add(StandardOpenOption.CREATE);
            writeOptions.add(StandardOpenOption.TRUNCATE_EXISTING);
        }

        programArguments.setWriteOptions(writeOptions.toArray(new StandardOpenOption[0]));

        if (arguments.getAppend()) {
            programArguments.setDoneMessage(MessageStrings.APPEND.getMessage());
        } else {
            programArguments.setDoneMessage(MessageStrings.CREATE.getMessage());
        }

        if (next != null) {
            next.process(arguments, programArguments);
        }
    }
}

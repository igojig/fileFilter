package ru.igojig.filefilter.argsprocessor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.filefilter.args.Arguments;
import ru.igojig.filefilter.args.DefaultValues;
import ru.igojig.filefilter.args.ProgramArguments;

import java.util.Optional;

/**
 * Валидирует префикс для выходных файлов (флаг "-p" {@link ru.igojig.filefilter.args.Arguments#outputPrefix})
 * Если префикс невалидный, устанавливается префикс по умолчанию {@link ru.igojig.filefilter.args.DefaultValues#DEFAULT_PREFIX}
 */
@Log4j2
public class OutputPrefixProcessor extends AbstractProcessor {
    private final String REGEX_FILENAME_PATTERN = "^[\\sA-Za-z0-9_-]{1,255}$";

    @Override
    public void process(Arguments arguments, ProgramArguments programArguments) {
        String prefix = arguments.getOutputPrefix();

        Optional<String> optional = checkPrefix(prefix);

        if (optional.isEmpty()) {
            log.warn("Prefix will be set to default");
            arguments.setOutputPrefix(DefaultValues.DEFAULT_PREFIX.getDefaultValue());
        }

        if (next != null) {
            next.process(arguments, programArguments);
        }

    }

    private Optional<String> checkPrefix(String prefix) {
        if (prefix == null) {
            log.warn("Prefix is null");
            return Optional.empty();
        }
        if (prefix.isEmpty()) {
            return Optional.of(prefix);
        }

        if (!prefix.matches(REGEX_FILENAME_PATTERN)) {
            log.warn("Prefix contains invalid characters: {}", prefix);
            return Optional.empty();
        }

        return Optional.of(prefix);

    }
}

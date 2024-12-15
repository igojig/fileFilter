package ru.igojig.args.processor;

import lombok.extern.log4j.Log4j2;
import ru.igojig.args.Arguments;
import ru.igojig.args.DefaultValues;
import ru.igojig.args.EffectiveParameters;

import java.util.Optional;

@Log4j2
public class OutputPrefixProcessor extends AbstractProcessor {
    private final String REGEX_FILENAME_PATTERN = "^[\\sA-Za-z0-9._]{1,255}$";

    @Override
    public void process(Arguments arguments, EffectiveParameters effectiveParameters) {
        String prefix= arguments.getOutputPrefix();

        Optional<String> optional = checkPrefix(prefix);

        if(optional.isEmpty()){
            log.warn("Prefix will be set to default");
            arguments.setOutputPrefix(DefaultValues.DEFAULT_PREFIX.getDefaultValue());
        }

        if(next!=null){
            next.process(arguments, effectiveParameters);
        }

    }

    private Optional<String> checkPrefix(String prefix){
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

package ru.igojig.filefilter.args;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Сообщения в зависимости от того, был ли установлен флаг "-a"{@link Arguments#append}
 */
@AllArgsConstructor
@Getter
public enum MessageStrings {
    CREATE("was created"),
    APPEND("was appended");

    private final String message;
}

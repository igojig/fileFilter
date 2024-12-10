package ru.igojig.checker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckStatus {
    private boolean fixInputFilenames;
    private boolean fixOutputPrefix;
    private boolean fixOutputPath;
}

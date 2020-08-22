package cz.jsochna.demo.logik.model;

import cz.jsochna.demo.logik.color_strategy.ColorRepeatStrategy;
import cz.jsochna.demo.logik.color_strategy.OnlyOnceColorRepeatStrategy;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GameConfig {

    @Getter
    EnabledColors enabledColors;

    @Getter
    int solutionLength;

    @Builder.Default
    ColorRepeatStrategy strategy = new OnlyOnceColorRepeatStrategy();

}

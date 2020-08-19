package cz.jsochna.demo.logik.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameConfig {

    @Getter
    EnabledColors enabledColors;

    @Getter
    short solutionLength;

    public GameConfig(int solutionLength, int colors) {
        this(solutionLength, new EnabledColors(colors));
    }

    public GameConfig(int solutionLength, String inputColors) {
        this(solutionLength, new EnabledColors(inputColors));
        this.solutionLength = (short) solutionLength;
    }

    public GameConfig(int solutionLength, EnabledColors enabledColors) {
        this.solutionLength = (short) solutionLength;
        this.enabledColors = enabledColors;
    }
}

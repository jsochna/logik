package cz.jsochna.demo.logik.model;

import lombok.Getter;
import lombok.ToString;

import java.util.EnumSet;
import java.util.Set;

@Getter
@ToString
public class GameConfig {

    EnumSet<SchemaColor> enabledColors;

    @Getter
    short solutionLength;

    public GameConfig(int solutionLength, int colors) {
        this(solutionLength, generateSchema(colors));
    }

    public GameConfig(int solutionLength, Set<SchemaColor> enabledColors) {
        this.enabledColors = EnumSet.copyOf(enabledColors);
        this.solutionLength = (short) solutionLength;
    }

    private static EnumSet<SchemaColor> generateSchema(int colors) {
        EnumSet<SchemaColor> colorsToEnable = EnumSet.noneOf(SchemaColor.class);
        for (var color : SchemaColor.values()) {
            if (colorsToEnable.size() > colors) break;
            colorsToEnable.add(color);
        }
        if (colors > colorsToEnable.size()) throw new IllegalArgumentException("There's not that many colors available");
        return colorsToEnable;
    }


}

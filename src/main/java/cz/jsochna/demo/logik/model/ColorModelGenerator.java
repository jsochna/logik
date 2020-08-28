package cz.jsochna.demo.logik.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ColorModelGenerator {
    private static final String ALL_COLORS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private ColorModelGenerator() {
        // hidden constructor
    }

    public static EnabledColors alphabetic(int limit) {
        return fromString(ALL_COLORS.substring(0, limit));
    }

    public static EnabledColors fromString(String chars) {
        Assert.state(StringUtils.containsOnly(chars, ALL_COLORS), "Input contains disallowed characters");
        List<Color> enabledChars = new ArrayList<>(chars.length());
        for (char c : chars.toCharArray()) {
            enabledChars.add(new CharacterColor(c));
        }

        return new EnabledColors(enabledChars);
    }

    public static EnabledColors fromList(Color... colors) {
        return new EnabledColors(Arrays.asList(colors));
    }

    public static EnabledColors subset(int size, Color[] colors) {
        List<Color> colorList = Arrays.asList(colors);
        return new EnabledColors(colorList.subList(0, size));
    }
}

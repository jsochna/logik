package cz.jsochna.demo.logik.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class EnabledColors {
    private static final String ALL_COLORS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final String enabledOnly;
    final char[] enabledChars;

    public EnabledColors(int limit) {
        enabledOnly = ALL_COLORS.substring(0, limit);
        enabledChars = enabledOnly.toCharArray();
    }

    public EnabledColors(String chars) {
        Assert.state(StringUtils.containsOnly(chars, ALL_COLORS), "Input contains disallowed characters");
        enabledOnly = chars;
        enabledChars = chars.toCharArray();
    }

    public char[] getColors() {
        return enabledChars;
    }


}

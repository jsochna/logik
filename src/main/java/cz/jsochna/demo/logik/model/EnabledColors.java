package cz.jsochna.demo.logik.model;

import lombok.ToString;

import java.util.Collection;
import java.util.List;

@ToString
public class EnabledColors {
    final List<Color> enabledChars;

    public EnabledColors(Collection<Color> colors) {
        this.enabledChars = List.copyOf(colors);
    }

    public List<Color> getColors() {
        return enabledChars;
    }


}

package cz.jsochna.demo.logik.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Guess {
    List<Color> colors;

    private Guess(List<Color> c) {
        this.colors = c;
    }

    public static Guess of(char[] input) {
        List<Color> colors = new ArrayList<>(input.length);
        for (char c : input) {
            colors.add(new CharacterColor(c));
        }
        return new Guess(colors);
    }

    public static Guess of(String input) {
        return of(input.toCharArray());
    }

    public static Guess of(Color... colorInput) {
        return new Guess(Arrays.asList(colorInput));
    }

    public List<Color> getBits() {
        return colors;
    }
}

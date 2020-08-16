package cz.jsochna.demo.logik.model;

import java.util.Collections;
import java.util.List;

public class Guess {
    List<Color> colors;

    public static Guess of(Color... bits) {
        Guess guess = new Guess();
        guess.colors = List.of(bits);
        return guess;
    }

    public List<Color> getBits() {
        return Collections.unmodifiableList(colors);
    }
}

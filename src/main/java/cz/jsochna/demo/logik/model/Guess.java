package cz.jsochna.demo.logik.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@EqualsAndHashCode
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

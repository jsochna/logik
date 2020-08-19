package cz.jsochna.demo.logik.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Guess {
    String colors;

    public static Guess of(char[] input) {
        Guess guess = new Guess();
        guess.colors = String.copyValueOf(input);
        return guess;
    }

    public static Guess of(String input) {
        Guess guess = new Guess();
        guess.colors = input;
        return guess;
    }

    public char[] getBits() {
        return colors.toCharArray();
    }
}

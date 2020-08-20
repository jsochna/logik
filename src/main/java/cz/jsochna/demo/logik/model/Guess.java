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

    public static Guess of(SchemaColor... colorInput) {
        char[] chars = new char[colorInput.length];
        for (int i = 0; i < colorInput.length; i++) {
            chars[i] = colorInput[i].toChar();
        }
        return Guess.of(chars);
    }

    public char[] getBits() {
        return colors.toCharArray();
    }
}

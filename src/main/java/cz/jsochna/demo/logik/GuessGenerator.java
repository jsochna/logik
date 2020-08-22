package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.color_strategy.ColorRepeatStrategy;
import cz.jsochna.demo.logik.model.GameConfig;
import cz.jsochna.demo.logik.model.Guess;

import java.util.Arrays;
import java.util.function.Supplier;

public class GuessGenerator implements Supplier<Guess> {
    final int solutionLength;
    final char[] availableColors;
    final byte[] bitsPosition;
    GameConfig config;

    ColorRepeatStrategy validator;

    public GuessGenerator(GameConfig config) {
        this.config = config;
        availableColors = config.getEnabledColors().getColors();
        solutionLength = config.getSolutionLength();
        bitsPosition = new byte[solutionLength];
        Arrays.fill(bitsPosition, (byte) 0);
        bitsPosition[0] = -1;

        this.validator = config.getStrategy();
    }

    private boolean increasePosition() {
        for (int i = 0; i < solutionLength; i++) {
            var newBit = ++bitsPosition[i];
            if (newBit == availableColors.length) {
                newBit = 0;
                bitsPosition[i] = newBit;
            } else {
                assert bitsPosition[i] == newBit;
                return true;
            }
        }
        return false;
    }

    private Guess convertBitsToGuess() {
        char[] guessBits = new char[bitsPosition.length];

        for (int i=0; i<bitsPosition.length; i++) {
            guessBits[i] = bitToColor(bitsPosition[i]);
        }

        return Guess.of(guessBits);
    }

    private char bitToColor(byte offset) {
        assert offset < availableColors.length;
        return this.availableColors[offset];
    }


    @Override
    public Guess get() {
        Guess guess;
        do {
            boolean ok = increasePosition();
            if (!ok) return null;
            guess = convertBitsToGuess();
        } while (! validator.isValidInStrategy(guess));

        return guess;
    }
}

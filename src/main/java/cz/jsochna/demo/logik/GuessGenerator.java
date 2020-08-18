package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Color;
import cz.jsochna.demo.logik.model.GameConfig;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.SchemaColor;

import java.util.Arrays;
import java.util.function.Supplier;

public class GuessGenerator implements Supplier<Guess> {
    final int solutionLength;
    final Color[] availableColors;
    final byte[] bitsPosition;
    GameConfig config;

    public GuessGenerator(GameConfig config) {
        this.config = config;
        availableColors = config.getEnabledColors().toArray(new SchemaColor[0]);
        solutionLength = config.getSolutionLength();
        bitsPosition = new byte[solutionLength];
        Arrays.fill(bitsPosition, (byte) 0);
        bitsPosition[0] = -1;
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
        Color[] guessBits = new Color[bitsPosition.length];

        for (int i=0; i<bitsPosition.length; i++) {
            guessBits[i] = bitToColor(bitsPosition[i]);
        }

        return Guess.of(guessBits);
    }

    private Color bitToColor(byte b) {
        assert b < availableColors.length;
        return this.availableColors[b];
    }


    @Override
    public Guess get() {
        boolean ok = increasePosition();
        if (!ok) return null;

        return convertBitsToGuess();
    }
}

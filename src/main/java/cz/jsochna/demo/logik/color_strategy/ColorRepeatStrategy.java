package cz.jsochna.demo.logik.color_strategy;

import cz.jsochna.demo.logik.model.Guess;

public interface ColorRepeatStrategy {
    boolean isValidInStrategy(Guess guess);
}

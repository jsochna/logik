package cz.jsochna.demo.logik.color_strategy;

import cz.jsochna.demo.logik.model.Guess;

public class RepeatsAllowedColorStrategy implements ColorRepeatStrategy {

    @Override
    public boolean isValidInStrategy(Guess guess) {
        return true;
    }
}

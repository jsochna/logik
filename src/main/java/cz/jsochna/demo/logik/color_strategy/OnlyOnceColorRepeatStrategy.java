package cz.jsochna.demo.logik.color_strategy;

import cz.jsochna.demo.logik.model.Color;
import cz.jsochna.demo.logik.model.Guess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OnlyOnceColorRepeatStrategy implements ColorRepeatStrategy {
    @Override
    public boolean isValidInStrategy(Guess guess) {
        List<Color> list = guess.getBits();
        Set<Color> s = new HashSet<>(list);

        return (s.size() == guess.getBits().size());
    }
}

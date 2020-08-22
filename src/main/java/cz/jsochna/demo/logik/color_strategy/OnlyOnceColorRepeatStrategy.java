package cz.jsochna.demo.logik.color_strategy;

import cz.jsochna.demo.logik.model.Guess;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OnlyOnceColorRepeatStrategy implements ColorRepeatStrategy {
    @Override
    public boolean isValidInStrategy(Guess guess) {
        Character[] characterArray = ArrayUtils.toObject(guess.getBits());
        List<Character> list = Arrays.asList(characterArray);
        Set<Character> s = new HashSet<>(list);

        return (s.size() == guess.getBits().length);
    }
}

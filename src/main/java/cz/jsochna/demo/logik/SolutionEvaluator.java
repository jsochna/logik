package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Color;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Predicate;

public class SolutionEvaluator {

    public GuessResult evaluate(Guess guess, Guess solution) {

        var gL = guess.getBits();
        var sL = solution.getBits();

        Map<Color, Integer> solutionCardinality = getCardinalityMap(sL);

        int blacks = 0;
        List<Integer> positionsToTestForWhite = new ArrayList<>();
        for (int i=0; i<gL.size(); i++) {
            var gc = gL.get(i);
            var sc = sL.get(i);

            if (Objects.equals(gc, sc)) {
                blacks++;
                lowerCardinality(gc, solutionCardinality);
            } else {
                positionsToTestForWhite.add(i);
            }
        }


        Predicate<Integer> evaluateMispositionedColors = i -> {
            var gC = gL.get(i);
            @NonNull
            Integer cardinality = solutionCardinality.getOrDefault(gC, 0);
            if (cardinality > 0) {
                lowerCardinality(gC, solutionCardinality);
                return true;
            }

            return false;
        };

        int whites = (int) positionsToTestForWhite.stream()
                .filter(evaluateMispositionedColors)
                .count();
        return GuessResult.of(blacks, whites);
    }

    private Map<Color, Integer> getCardinalityMap(Collection<Color> sL) {
        return CollectionUtils.getCardinalityMap(sL);
    }

    private void lowerCardinality(Color gc, Map<Color, Integer> solutionCardinality) {
        solutionCardinality.computeIfPresent(gc, (color, integer) -> (--integer));
    }
}

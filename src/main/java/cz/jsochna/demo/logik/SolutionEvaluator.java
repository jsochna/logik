package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import cz.jsochna.demo.logik.model.SolutionColor;

import java.util.*;
import java.util.function.Consumer;

public class SolutionEvaluator {

    public GuessResult evaluate(Guess guess, Guess solution) {

        var gL = guess.getBits();
        var sL = solution.getBits();

        Map<Character, Integer> solutionCardinality = getCardinalityMap(sL);

        List<SolutionColor> result = new LinkedList<>();
        List<Integer> positionsToTestForWhite = new ArrayList<>();
        for (int i=0; i<gL.length; i++) {
            var gc = gL[i];
            var sc = sL[i];

            if (gc == sc) {
                result.add(SolutionColor.BLACK);
                lowerCardinality(gc, solutionCardinality);
            } else {
                positionsToTestForWhite.add(i);
            }
        }
        Consumer<Integer> evaluateMispositionedColors = i -> {
            var gC = gL[i];
            Integer cardinality = solutionCardinality.get(gC);
            if (cardinality != null && cardinality > 0) {
                lowerCardinality(gC, solutionCardinality);
                result.add(SolutionColor.WHITE);
            }
        };

        positionsToTestForWhite.forEach(evaluateMispositionedColors);
        return GuessResult.from(result);
    }

    private Map<Character, Integer> getCardinalityMap(char[] sL) {
        HashMap<Character, Integer> cardinality = new HashMap<>();
        for (char c : sL) {
            cardinality.compute(c, (k, v) -> Optional.ofNullable(v).orElse(0) + 1);
        }
        return cardinality;
    }

    private void lowerCardinality(Character gc, Map<Character, Integer> solutionCardinality) {
        solutionCardinality.computeIfPresent(gc, (color, integer) -> (--integer));
    }
}

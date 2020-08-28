package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Color;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import cz.jsochna.demo.logik.model.SolutionColor;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

public class SolutionEvaluator {

    public GuessResult evaluate(Guess guess, Guess solution) {

        var gL = guess.getBits();
        var sL = solution.getBits();

        Map<Color, Integer> solutionCardinality = getCardinalityMap(sL);

        List<SolutionColor> result = new LinkedList<>();
        List<Integer> positionsToTestForWhite = new ArrayList<>();
        for (int i=0; i<gL.size(); i++) {
            var gc = gL.get(i);
            var sc = sL.get(i);

            if (Objects.equals(gc, sc)) {
                result.add(SolutionColor.BLACK);
                lowerCardinality(gc, solutionCardinality);
            } else {
                positionsToTestForWhite.add(i);
            }
        }
        Consumer<Integer> evaluateMispositionedColors = i -> {
            var gC = gL.get(i);
            @NonNull
            Integer cardinality = solutionCardinality.getOrDefault(gC, 0);
            if (cardinality > 0) {
                lowerCardinality(gC, solutionCardinality);
                result.add(SolutionColor.WHITE);
            }
        };

        positionsToTestForWhite.forEach(evaluateMispositionedColors);
        return GuessResult.from(result);
    }

    private Map<Color, Integer> getCardinalityMap(Collection<Color> sL) {
        return CollectionUtils.getCardinalityMap(sL);
    }

    private void lowerCardinality(Color gc, Map<Color, Integer> solutionCardinality) {
        solutionCardinality.computeIfPresent(gc, (color, integer) -> (--integer));
    }
}

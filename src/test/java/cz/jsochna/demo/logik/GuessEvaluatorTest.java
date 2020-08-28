package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import cz.jsochna.demo.logik.model.SolutionColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GuessEvaluatorTest {
    SolutionEvaluator evaluator = new SolutionEvaluator();

    @Test
    void correctGuessSolution() {
        var guess = Guess.of("AA");
        var solution = guess;

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(2, 0));
    }

    @Test
    void mismatch() {
        var guess = Guess.of("AB");
        var solution = Guess.of("CD");

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(0, 0));
    }

    @Test
    void partialMatch() {
        var guess = Guess.of("AB");
        var solution = Guess.of("BC");

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(0, 1));
    }

    @Test
    void partialMatch2() {
        var guess = Guess.of("BA");
        var solution = Guess.of("CB");

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(SolutionColor.WHITE));
    }

    @Test
    void multipleColors() {
        var guess = Guess.of("ABA");
        var solution = Guess.of("ABC");

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(2, 0));
    }


}

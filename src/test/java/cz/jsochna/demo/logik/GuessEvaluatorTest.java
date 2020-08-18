package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import cz.jsochna.demo.logik.model.SolutionColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static cz.jsochna.demo.logik.model.SchemaColor.*;
import static cz.jsochna.demo.logik.model.SolutionColor.BLACK;

class GuessEvaluatorTest {
    SolutionEvaluator evaluator = new SolutionEvaluator();

    @Test
    void correctGuessSolution() {
        var guess = Guess.of(RED, RED);
        var solution = guess;

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(BLACK, BLACK));
    }

    @Test
    void mismatch() {
        var guess = Guess.of(RED, BLUE);
        var solution = Guess.of(GREEN, PINK);

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of());
    }

    @Test
    void partialMatch() {
        var guess = Guess.of(RED, BLUE);
        var solution = Guess.of(BLUE, GREEN);

        GuessResult result = evaluator.evaluate(guess, solution);
        Assertions.assertThat(result).isEqualTo(GuessResult.of(SolutionColor.WHITE));
    }


}

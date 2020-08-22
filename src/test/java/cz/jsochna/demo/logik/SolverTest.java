package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.color_strategy.OnlyOnceColorRepeatStrategy;
import cz.jsochna.demo.logik.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static cz.jsochna.demo.logik.model.SchemaColor.*;
import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    Board board;
    Solver solver;

    @Test
    void guessInLineWithPreviousResults() {
        board = new Board(GameConfig.builder()
                .solutionLength(1)
                .enabledColors(new EnabledColors("ABC"))
                .build());
        solver = new Solver(board);

        board.recordGuess(Guess.of("A"), GuessResult.of());
        board.recordGuess(Guess.of("B"), GuessResult.of());

        Guess guessedSolution = solver.generateGuess();

        assertThat(guessedSolution).isEqualTo(Guess.of("C"));
    }

    @Test
    void gotColorsWithoutPosition() {
        board = new Board(GameConfig.builder()
                .solutionLength(2)
                .enabledColors(new EnabledColors("ABCD"))
                .build());
        solver = new Solver(board);

        board.recordGuess(Guess.of("AB"), GuessResult.of(0, 2));

        Guess guessedSolution = solver.generateGuess();

        assertThat(guessedSolution).isEqualTo(Guess.of("BA"));
    }

    @Test
    void realGame() {
        board = new Board(GameConfig.builder()
                .solutionLength(4)
                .enabledColors(new EnabledColors(7))
                .strategy(new OnlyOnceColorRepeatStrategy())
                .build());
        solver = new Solver(board);

        board.recordGuess(Guess.of(RED, GREEN, ORANGE, BLUE), GuessResult.of(1, 1));
        board.recordGuess(Guess.of(RED, YELLOW, GREEN, PINK), GuessResult.of(0, 3));
        board.recordGuess(Guess.of(YELLOW, RED, PINK, RED), GuessResult.of(0, 2));
        board.recordGuess(Guess.of(GREEN, PINK, RED, YELLOW), GuessResult.of(1, 2));
        board.recordGuess(Guess.of(PINK, GREEN, BLUE, YELLOW), GuessResult.of(2, 0));
        board.recordGuess(Guess.of(PINK, RED, ORANGE, YELLOW), GuessResult.of(1, 1));

        Guess guessedSolution = solver.generateGuess();

        Assertions.assertThat(guessedSolution).isEqualTo(Guess.of(PINK, GREEN, RED, SWHITE));
    }

    @Test
    void realGame2() {
        board = new Board(GameConfig.builder()
                .solutionLength(5)
                .enabledColors(new EnabledColors(8))
                .strategy(new OnlyOnceColorRepeatStrategy())
                .build());
        solver = new Solver(board);

        board.recordGuess(Guess.of(RED, GREEN, ORANGE, BLUE, YELLOW), GuessResult.of(2, 2));
        board.recordGuess(Guess.of(YELLOW, GREEN, ORANGE, RED, SWHITE), GuessResult.of(1, 2));
        board.recordGuess(Guess.of(RED, GREEN, PINK, SBLACK, BLUE), GuessResult.of(1, 3));
        board.recordGuess(Guess.of(YELLOW, RED, PINK, BLUE, ORANGE), GuessResult.of(1, 2));
        board.recordGuess(Guess.of(BLUE, SBLACK, GREEN, ORANGE, RED), GuessResult.of(1, 3));
        board.recordGuess(Guess.of(GREEN, SBLACK, BLUE, YELLOW, PINK), GuessResult.of(0, 4));
        board.recordGuess(Guess.of(YELLOW, GREEN, SBLACK, BLUE, RED), GuessResult.of(3, 2));

        Guess guessedSolution = solver.generateGuess();

        Assertions.assertThat(guessedSolution).isEqualTo(Guess.of(SBLACK, GREEN, YELLOW, BLUE, RED));
        System.out.println(guessedSolution);
    }

}

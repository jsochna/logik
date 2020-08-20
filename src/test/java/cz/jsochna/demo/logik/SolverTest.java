package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static cz.jsochna.demo.logik.model.SchemaColor.*;
import static cz.jsochna.demo.logik.model.SolutionColor.BLACK;
import static cz.jsochna.demo.logik.model.SolutionColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    SolutionEvaluator evaluator = new SolutionEvaluator();

    Board board;
    Solver solver;

    @BeforeEach
    void resetBoard() {
    }

    @Test
    void guessInLineWithPreviousResults() {
        board = new Board(new GameConfig(1, "ABC"));
        solver = new Solver(board);

        board.recordGuess(Guess.of("A"), GuessResult.of());
        board.recordGuess(Guess.of("B"), GuessResult.of());

        Guess guessedSolution = solver.generateGuess();

        assertThat(guessedSolution).isEqualTo(Guess.of("C"));
    }

    @Test
    void gotColorsWithoutPosition() {
        board = new Board(new GameConfig(2, "ABC"));
        solver = new Solver(board);

        board.recordGuess(Guess.of("AB"), GuessResult.of(WHITE, WHITE));

        Guess guessedSolution = solver.generateGuess();

        assertThat(guessedSolution).isEqualTo(Guess.of("BA"));
    }

    @Test
    @Disabled
    void realGame() {
        board = new Board(new GameConfig(4, 7));
        solver = new Solver(board);

        board.recordGuess(Guess.of(RED, GREEN, ORANGE, BLUE), GuessResult.of(BLACK, WHITE));
        board.recordGuess(Guess.of(RED, YELLOW, GREEN, PINK), GuessResult.of(WHITE, WHITE, WHITE));
        board.recordGuess(Guess.of(YELLOW, RED, PINK, RED), GuessResult.of(WHITE, WHITE));
        board.recordGuess(Guess.of(GREEN, PINK, RED, YELLOW), GuessResult.of(BLACK, WHITE, WHITE));
        board.recordGuess(Guess.of(PINK, GREEN, BLUE, YELLOW), GuessResult.of(BLACK, BLACK));

        Guess guessedSolution = solver.generateGuess();

        System.out.println(guessedSolution);

    }

}

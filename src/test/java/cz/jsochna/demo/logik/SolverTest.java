package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Board;
import cz.jsochna.demo.logik.model.GameConfig;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.GuessResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void gotColors() {
        board = new Board(new GameConfig(2, 3)); // BLUE, GREEN, RED
        solver = new Solver(board);

        board.recordGuess(Guess.of("AB"), GuessResult.of(WHITE, WHITE));

        Guess guessedSolution = solver.generateGuess();

        assertThat(guessedSolution).isEqualTo(Guess.of("BA"));
    }


}

package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.color_strategy.OnlyOnceColorRepeatStrategy;
import cz.jsochna.demo.logik.color_strategy.RepeatsAllowedColorStrategy;
import cz.jsochna.demo.logik.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static cz.jsochna.demo.logik.model.SchemaColor.*;
import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    Board board;
    Solver solver;

    SolutionEvaluator evaluator = new SolutionEvaluator();

    @Nested
    class SimpleCases {
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
    }

    @Nested
    class FinishGameWithUser {
        @Test
        void realGameLen4() {
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
        void realGameLen5() {
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

    @Nested
    class AutomaticGame {
        @Test
        void colorsDontRepeatLength4() {
            board = new Board(GameConfig.builder()
                    .solutionLength(4)
                    .enabledColors(new EnabledColors(7))
                    .strategy(new OnlyOnceColorRepeatStrategy())
                    .build());
            Guess solution = Guess.of(PINK, BLUE, RED, YELLOW);

            playGame(solution, 10);
        }

        @Test
        void repeatsAllowedLen4() {
            board = new Board(GameConfig.builder()
                    .solutionLength(4)
                    .enabledColors(new EnabledColors(7))
                    .strategy(new RepeatsAllowedColorStrategy())
                    .build());
            Guess solution = Guess.of(PINK, BLUE, RED, YELLOW);

            playGame(solution, 20);
        }

        private void playGame(Guess solution, final int roundLimit) {
            solver = new Solver(board);
            int attempts = 0;
            GuessResult evaluation;

            do {
                if (++attempts > roundLimit) throw new IllegalStateException("Did not find solution fast enough");
                Guess guess = solver.generateGuess();
                evaluation = evaluator.evaluate(guess, solution);
                System.out.printf("Round %d: %s (%s)\n", attempts, guess, evaluation);
                board.recordGuess(guess, evaluation);
            } while (evaluation.getBlack() < board.getConfig().getSolutionLength());
        }
    }
}

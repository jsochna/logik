package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Board;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.Round;

import java.util.Objects;
import java.util.stream.Stream;

public class Solver {

    Board board;

    GuessGenerator generator;

    Stream<Guess> guessStream;
    private SolutionEvaluator evaluator;

    Solver(Board board) {
        this.board = board;
        generator = new GuessGenerator(board.getConfig());
        guessStream = Stream.generate(generator);
        evaluator = new SolutionEvaluator();
    }

    public Guess generateGuess() {
        var guess = guessStream
                .takeWhile(Objects::nonNull)
                .filter(this::validateGuessAgainstPastResults)
                .findFirst();

        if (guess.isEmpty()) throw new IllegalArgumentException("There is no solution");
        return guess.get();
    }

    private boolean validateGuessAgainstPastResults(Guess guessedSolution) {
        for (Round r : board.getRounds()) {
            var resultAgainstThisGuess = evaluator.evaluate(r.getGuess(), guessedSolution);
            if (!resultAgainstThisGuess.equals(r.getResult())) return false;
        }

        return true;
    }

}

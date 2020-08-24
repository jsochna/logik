package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.model.Board;
import cz.jsochna.demo.logik.model.Guess;
import cz.jsochna.demo.logik.model.Round;

import java.util.Objects;
import java.util.stream.Stream;

public class Solver {

    private final Board board;

    private final GuessGenerator generator;

    private final SolutionEvaluator evaluator;

    Solver(Board board) {
        this.board = board;
        generator = new GuessGenerator(board.getConfig());
        evaluator = new SolutionEvaluator();
    }

    public Guess generateGuess() {
        var guessStream = Stream.generate(generator);
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

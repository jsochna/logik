package cz.jsochna.demo.logik.model;

import lombok.Getter;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Getter
    GameConfig config;
    List<Round> rounds = new ArrayList<>();

    public Board(GameConfig config) {
        this.config = config;
    }

    public void clear() {
        rounds.clear();
    }

    public void recordGuess(Guess guess, GuessResult result) {
        Assert.state(guess.getBits().size() == config.solutionLength,
                "Solution length doesn't match the config");
        guess.getBits().forEach(color -> Assert.state(config.getEnabledColors().getColors().contains(color),
            "Guess uses alternative set of colors"));
        var round = new Round(guess, result);
        rounds.add(round);
    }

    public Iterable<Round> getRounds() {
        return IterableUtils.unmodifiableIterable(rounds);
    }
}

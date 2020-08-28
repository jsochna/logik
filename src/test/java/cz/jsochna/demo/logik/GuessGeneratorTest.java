package cz.jsochna.demo.logik;

import cz.jsochna.demo.logik.color_strategy.RepeatsAllowedColorStrategy;
import cz.jsochna.demo.logik.model.ColorModelGenerator;
import cz.jsochna.demo.logik.model.GameConfig;
import cz.jsochna.demo.logik.model.Guess;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GuessGeneratorTest {


    @Test
    void allOptionsForTwoColors() {
        var config = simpleConfig()
                .build();
        var generator = new GuessGenerator(config);
        Stream<Guess> stream = Stream.generate(generator);

        List<Guess> allGuesses = stream
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());

        assertThat(allGuesses).containsExactlyInAnyOrder(
                Guess.of("A"),
                Guess.of("B")
        );
    }

    @Test
    void allOptionsForTwoColorsTwoSlots() {
        var config = simpleConfig().solutionLength(2).build();
        var generator = new GuessGenerator(config);
        Stream<Guess> stream = Stream.generate(generator);

        List<Guess> allGuesses = stream
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());

        assertThat(allGuesses).containsExactlyInAnyOrder(
                Guess.of("AA"),
                Guess.of("BB"),
                Guess.of("AB"),
                Guess.of("BA")
        );
    }

    @Test
    void allOptionsForThreeColorsTwoSlots() {
        var config = GameConfig.builder()
                .enabledColors(ColorModelGenerator.fromString("ABC"))
                .solutionLength(2)
                .strategy(new RepeatsAllowedColorStrategy())
                .build();
        var generator = new GuessGenerator(config);
        Stream<Guess> stream = Stream.generate(generator);

        List<Guess> allGuesses = stream
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());

        assertThat(allGuesses).containsExactlyInAnyOrder(
                Guess.of("AA"),
                Guess.of("AB"),
                Guess.of("AC"),
                Guess.of("BA"),
                Guess.of("BB"),
                Guess.of("BC"),
                Guess.of("CA"),
                Guess.of("CB"),
                Guess.of("CC")
        );
    }

    private GameConfig.GameConfigBuilder simpleConfig() {
        return GameConfig.builder()
                .solutionLength(1)
                .enabledColors(ColorModelGenerator.fromString("AB"))
                .strategy(new RepeatsAllowedColorStrategy());
    }

}
package cz.jsochna.demo.logik;

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
        var config = new GameConfig(1, "AB");
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

}
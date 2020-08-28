package cz.jsochna.demo.logik.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Getter
@ToString
public class GuessResult {
    private short black;
    private short white;

    public static GuessResult of(SolutionColor... bits) {
        return new GuessResult(Arrays.asList(bits));
    }
    public static GuessResult of(int black, int white) {
        return new GuessResult(black, white);
    }
    public static GuessResult from(Collection<SolutionColor> bits) {
        return new GuessResult(bits);
    }

    GuessResult(int black, int white) {
        this.black = (short) black;
        this.white = (short) white;
    }

    GuessResult(Collection<SolutionColor> colors) {
        for (SolutionColor c : colors) {
            if (c.equals(SolutionColor.BLACK)) {
                black++;
            } else if (c.equals(SolutionColor.WHITE)) {
                white++;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuessResult)) return false;
        GuessResult that = (GuessResult) o;
        return black == that.black &&
                white == that.white;
    }

    @Override
    public int hashCode() {
        return Objects.hash(black, white);
    }
}

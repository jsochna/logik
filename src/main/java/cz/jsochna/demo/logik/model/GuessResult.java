package cz.jsochna.demo.logik.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuessResult {
    List<SolutionColor> colors;

    public static GuessResult of(SolutionColor... bits) {
        GuessResult guess = new GuessResult();
        guess.colors = List.of(bits);
        return guess;
    }
    public static GuessResult from(Collection<SolutionColor> bits) {
        GuessResult guess = new GuessResult();
        guess.colors = new ArrayList<>(bits);
        return guess;
    }


    @Override
    public String toString() {
        return "GuessResult{" +
                "colors=" + colors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GuessResult)) return false;

        GuessResult that = (GuessResult) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(colors, that.colors)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(colors)
                .toHashCode();
    }
}

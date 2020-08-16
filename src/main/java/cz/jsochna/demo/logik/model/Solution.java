package cz.jsochna.demo.logik.model;

import java.util.EnumMap;

public class Solution {
    EnumMap<SolutionColor, Integer> counts = new EnumMap<>(SolutionColor.class);

    void clear() {
        counts.clear();
    }


}

package prog2.exercises.set03;

import java.util.HashMap;
import java.util.Map;

public class TSPBoards {
    public final static Map<Integer[], String> def_board1 = new HashMap<>(Map.of(
            new Integer[]{1, 1}, "A",
            new Integer[]{1, 2}, "B",
            new Integer[]{2, 1}, "C",
            new Integer[]{2, 2}, "D",
            new Integer[]{3, 1}, "E",
            new Integer[]{3, 2}, "F"
    ));

    public final static Map<Integer[], String> def_board2 = new HashMap<>(Map.ofEntries(
            Map.entry(new Integer[]{1, 1}, "A"),
            Map.entry(new Integer[]{1, 2}, "B"),
            Map.entry(new Integer[]{1, 3}, "C"),
            Map.entry(new Integer[]{1, 4}, "D"),
            Map.entry(new Integer[]{2, 2}, "E"),
            Map.entry(new Integer[]{2, 3}, "F"),
            Map.entry(new Integer[]{4, 1}, "G"),
            Map.entry(new Integer[]{4, 4}, "H"),
            Map.entry(new Integer[]{4, 5}, "I"),
            Map.entry(new Integer[]{4, 6}, "J"),
            Map.entry(new Integer[]{4, 7}, "K")
    ));

    public final static Map<Integer[], String> def_board3 = new HashMap<>(Map.ofEntries(
            Map.entry(new Integer[]{1, 1}, "A"),
            Map.entry(new Integer[]{1, 2}, "B"),
            Map.entry(new Integer[]{1, 3}, "C"),
            Map.entry(new Integer[]{1, 4}, "D"),
            Map.entry(new Integer[]{2, 2}, "E"),
            Map.entry(new Integer[]{2, 3}, "F"),
            Map.entry(new Integer[]{4, 1}, "G"),
            Map.entry(new Integer[]{4, 4}, "H"),
            Map.entry(new Integer[]{4, 5}, "I"),
            Map.entry(new Integer[]{4, 6}, "J"),
            Map.entry(new Integer[]{4, 7}, "K"),
            Map.entry(new Integer[]{5, 1}, "L"),
            Map.entry(new Integer[]{5, 4}, "M"),
            Map.entry(new Integer[]{5, 5}, "N"),
            Map.entry(new Integer[]{5, 6}, "O")
    ));
}

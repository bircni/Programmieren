// Teamarbeit Fabian Lippold & Nicolas Bircks

package prog2.exercises.set03;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        Locale.setDefault(Locale.US);
        UndirectedGraph board;

        System.out.println("------- Traveling Salesman Problem solver ------- ");
        System.out.println("Available 'Leiterplattenbohrprobleme':\n\tBoard 1 (A-E)\n\tBoard 2 (A-K)\n\tBoard 3 (A-O)\n");
        System.out.print("Which board do you want? [1,2,3]: ");

        int nBytes = System.getProperty("os.name").contains("Linux") ? 2 : 3;

        int b = System.in.readNBytes(nBytes)[0] - '1' + 1;
        switch (b) {
            case 1 -> board = boardToGraph(TSPBoards.def_board1);
            case 2 -> board = boardToGraph(TSPBoards.def_board2);
            case 3 -> board = boardToGraph(TSPBoards.def_board3);
            default -> throw new NumberFormatException() {
                @Override
                public String getMessage() {
                    return "Board " + b + " not available";
                }
            };
        }
        board.writeDot("graph.dot");

        System.out.println("\nAvailable algorithms:\n\t1: bogoRoute\n\t2: Brute Force");
        System.out.print("Which algorithm do you want? [1,2]: ");

        int algo = System.in.readNBytes(nBytes)[0] - '1' + 1;
        System.out.write(10);

        switch (algo) {
            case 1 -> GraphAlgo.bogoRoute(board, 10);
            case 2 -> GraphAlgo.bruteForce(board);
            default -> throw new NumberFormatException() {
                @Override
                public String getMessage() {
                    return "Algorithm " + algo + " not available!";
                }
            };
        }
    }

    public static UndirectedGraph boardToGraph(final Map<Integer[], String> board) {
        Map<OrderedSingletonSet<Integer>, Double> graph = new HashMap<>();
        Map<Integer[], Integer> m = new HashMap<>();
        board.forEach((x, y) -> m.put(x, m.size()));
        board.keySet().forEach(coords1 ->
                board.keySet().stream().filter(coords2 -> coords1 != coords2).forEach(coords2 -> graph.put(new OrderedSingletonSet<>(m.get(coords1), m.get(coords2)), Math.sqrt(IntStream.range(0, 2).mapToDouble(i ->
                        Math.pow(Math.abs(coords1[i] - coords2[i]), 2)).sum()))
                ));
        return new UndirectedGraph(graph);
    }
}

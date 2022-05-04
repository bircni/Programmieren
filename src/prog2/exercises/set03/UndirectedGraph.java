package prog2.exercises.set03;

import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UndirectedGraph {
    public final Map<OrderedSingletonSet<Integer>, Double> adjList;
    public final double[][] adjMatrix;

    public UndirectedGraph(Map<OrderedSingletonSet<Integer>, Double> graph) {
        this.adjList = new Hashtable<>(graph);

        adjMatrix = new double[this.size() + 1][this.size() + 1];
        graph.forEach((k, v) -> adjMatrix[k.fst][k.snd] = v);
        graph.forEach((k, v) -> adjMatrix[k.snd][k.fst] = v);
        IntStream.range(0, this.size() + 1).forEach(x -> adjMatrix[x][x] = Double.MAX_VALUE);
    }

    public UndirectedGraph subgraph(int[] nodes) {
        Map<OrderedSingletonSet<Integer>, Double> subAdjList = new HashMap<>();

        for (OrderedSingletonSet<Integer> iss : adjList.keySet()) {
            if (Arrays.stream(nodes).boxed().collect(Collectors.toList()).containsAll(iss)) {
                subAdjList.put(iss, adjList.get(iss));
            }
        }
        return new UndirectedGraph(subAdjList);
    }

    public Map<Integer, Double> edgesWith(int node) {
        return adjList.keySet().stream().filter(x -> x.contains(node)).distinct().collect(Collectors.toMap(x -> x.not(node), adjList::get));
    }

    public double[] fastEdgesWith(int node) {
        return adjMatrix[node];
    }

    public int degree(int node) {
        return (int) edgesWith(node).keySet().stream().distinct().count();
    }

    public int size() {
        return adjList.keySet().stream().flatMap(Collection::stream).mapToInt(x -> x).max().orElse(0);
    }

    public int[] nodes() {
        return IntStream.range(1, size() + 1).toArray();
    }

    @Override
    public String toString() {
        return String.format("Graph with %d Vertices and %d Edges\n%s", size(), adjList.keySet().stream().distinct().count(), adjList.entrySet().stream().map(entry -> String.format("(%d - %d) weight=%f\n", entry.getKey().fst, entry.getKey().snd, entry.getValue())).collect(Collectors.joining()));
    }

    public void writeDot(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(String.format("graph {\n%s\n}", adjList.entrySet().stream().map(x -> (String.format("%d -- %d [weight=%f,label=%.3f]\n", x.getKey().fst, x.getKey().snd, x.getValue(), x.getValue()))).collect(Collectors.joining())));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

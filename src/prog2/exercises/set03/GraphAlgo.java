package prog2.exercises.set03;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphAlgo {
    public static native void fastBruteForceNative(double[] graph, int n);

    public static void bogoRoute(UndirectedGraph g, double time) {
        double bestRouteCost = Double.MAX_VALUE;
        int[] bestRoute = new int[g.size()];

        long start = System.nanoTime();

        while (System.nanoTime() <= start + time * Math.pow(10, 9)) {
            List<Integer> route = IntStream.range(0, g.size() + 1).boxed().collect(Collectors.toList());
            Collections.shuffle(route);
            double cost = IntStream.range(0, g.size()).mapToDouble(i -> g.edgesWith(route.get(i)).get(route.get(i + 1))).sum();
            if (cost < bestRouteCost) {
                bestRouteCost = cost;
                bestRoute = route.parallelStream().mapToInt(x -> x).toArray();
            }
        }
        System.out.printf("bogoRoute: %s cost: %f\n", Arrays.toString(bestRoute), bestRouteCost);
    }

    public static void fastBruteForce(UndirectedGraph g) {
        fastBruteForceNative(Arrays.stream(g.adjMatrix).flatMapToDouble(Arrays::stream).toArray(), g.size());
    }

    public static void bruteForce(UndirectedGraph g) {
        BigInteger numberOfPermutations = IntStream.range(1, g.size() + 2).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).orElseThrow();
        System.out.printf("Number of permutations to check: %,d\n", numberOfPermutations.longValue());

        AtomicReference<Double> bestCost = new AtomicReference<>(Double.MAX_VALUE);
        AtomicReference<int[]> bestRoute = new AtomicReference<>(new int[0]);

        AtomicLong progress = new AtomicLong(0);
        final int n = g.size();

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> System.out.printf("\r%,.2f%%  ", 100 * progress.get() / numberOfPermutations.doubleValue()), 0, 100, TimeUnit.MILLISECONDS);
        permute(n + 1, IntStream.range(0, n + 1).toArray(), (x) -> {
                    progress.incrementAndGet();
                    double cost = 0;
                    for (int i = 0; i < n; i++) cost += g.fastEdgesWith(x[i])[x[i + 1]];

                    if (cost < bestCost.get()) {
                        bestCost.set(cost);
                        bestRoute.set(x);
                    }
                    return null;
                }
        );
        es.shutdownNow();

        System.out.printf("\rbest route: %s cost: %.2f\n", Arrays.toString(bestRoute.get()), bestCost.get());
    }

    public static void permute(int n, int[] elements, Function<int[], Void> f) {
        if (n == 1) f.apply(elements);
        else {
            for (int i = 0; i < n - 1; i++) {
                permute(n - 1, elements, f);
                int temp;
                if (n % 2 == 0) {
                    temp = elements[i];
                    elements[i] = elements[n - 1];
                } else {
                    temp = elements[0];
                    elements[0] = elements[n - 1];
                }
                elements[n - 1] = temp;
            }
            permute(n - 1, elements, f);
        }
    }
}

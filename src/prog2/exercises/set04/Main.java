// Teamarbeit Fabian Lippold & Nicolas Bircks
package prog2.exercises.set04;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Aufgabe 1
        Train SantaFe = new Train(new Locomotive(23, 5311)).push_back(new Car(12, 50)).push_back(new Car(15, 75)).push_back(new Car(20, 100));
        Train RioGrandeExpress = new Train(new Locomotive(21, 5409)).push_back(new Car(13, 60)).push_back(new Car(18, 80));

        System.out.println(SantaFe);
        System.out.println();

        System.out.println(RioGrandeExpress);
        System.out.println();

        RioGrandeExpress.reverse();
        System.out.println(RioGrandeExpress);
        System.out.println();

        // Aufgabe 2
        Main.doManyOperations(ArrayList.class, 100000);
        System.out.println();
        doManyOperations(LinkedList.class, 100000);
    }

    public static <T extends List<Object>> void doManyOperations(Class<T> type, int n) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.printf("Testing %s performance: \n", type.getSimpleName());
        List<Object> l = type.getDeclaredConstructor().newInstance();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) l.add(i);
        System.out.printf("\t%-12s %-20s: \t%.3fs\n", type.getSimpleName(), "push front", (System.nanoTime() - start) / Math.pow(10, 9));

        start = System.nanoTime();
        for (int i = 0; i < n; i++) l.add(0, i);
        System.out.printf("\t%-12s %-20s: \t%.3fs\n", type.getSimpleName(), "push back", (System.nanoTime() - start) / Math.pow(10, 9));

        start = System.nanoTime();
        Object placebo = 0;
        for (int i = 0; i < n; i++) placebo = l.get(i);
        System.out.print(placebo + "\r");
        System.out.printf("\t%-12s %-20s: \t%.3fs\n", type.getSimpleName(), "sequential access", (System.nanoTime() - start) / Math.pow(10, 9));

        start = System.nanoTime();
        for (Object o : l) placebo = o;
        System.out.print(placebo + "\r");
        System.out.printf("\t%-12s %-20s: \t%.3fs\n", type.getSimpleName(), "iterative access", (System.nanoTime() - start) / Math.pow(10, 9));
    }
}

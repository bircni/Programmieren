package prog1.exercises.set09;
import java.util.ArrayList;

public class Insertionsort extends Algorithm {
    @Override
    public String toString() {
        return "Quicksort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Insertionsort(ArrayList<Integer> in, Main m) {
        this.m = m;
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));

        int n = tmp.size();
        compares++;
        for (int j = 1; j < n; j++) {
            compares++;

            int key = tmp.get(j);
            int i = j - 1;

            compares++;

            int swapped = 0;
            while ((i > -1) && (tmp.get(i) > key)) {
                compares++;

                tmp.set(i + 1, tmp.get(i));

                if (swapped++ % 2 == 0) {
                    swaps++;
                }

                i--;
            }

            if (j % 2 == 0) {
                swaps++;
            }
            tmp.set(i + 1, key);

            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }

        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }
}

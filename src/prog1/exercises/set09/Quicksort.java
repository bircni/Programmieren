package prog1.exercises.set09;
import java.util.ArrayList;

public class Quicksort extends Algorithm {

    @Override
    public String toString() {
        return "Quicksort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Quicksort(ArrayList<Integer> in, Main m) {
        this.m = m;
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));
        qs(0, steps.get(0).size() - 1, tmp);
        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }

    void qs(int li, int re, ArrayList<Integer> tmp) {
        int t;
        if (li < re) {
            compares++;
            t = teilen(li, re, tmp);
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
            qs(li, t, tmp);
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
            qs(t + 1, re, tmp);
        }
    }

    int teilen(int li, int re, ArrayList<Integer> tmp) {
        int i, j, pivot = tmp.get((li + re) / 2);
        i = li - 1;
        j = re + 1;
        while (true) {
            do {
                compares++;
                i++;
            } while (tmp.get(i) < pivot);
            do {
                compares++;
                j--;
            } while (tmp.get(j) > pivot);
            compares++;
            if (i < j) {
                int a = tmp.get(i);
                tmp.set(i, tmp.get(j));
                tmp.set(j, a);
                swaps++;
                steps.add(new ArrayList<>(tmp));
                stats.add(new Integer[]{swaps, compares});
            } else {
                steps.add(new ArrayList<>(tmp));
                stats.add(new Integer[]{swaps, compares});
                return j;
            }
        }
    }
}

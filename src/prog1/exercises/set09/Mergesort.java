package prog1.exercises.set09;
import java.util.ArrayList;

public class Mergesort extends Algorithm {

    @Override
    public String toString() {
        return "Mergesort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Mergesort(ArrayList<Integer> in, Main m) {
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        this.m = m;
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));
        sort(tmp, 0, tmp.size() - 1);
        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }

    void sort(ArrayList<Integer> tmp, int l, int r) {
        compares++;
        if (l < r) {
            int m = (l + r) / 2;

            sort(tmp, l, m);
            sort(tmp, m + 1, r);

            merge(tmp, l, m, r);
        }
        steps.add(new ArrayList<>(tmp));
        stats.add(new Integer[]{swaps, compares});
    }

    void merge(ArrayList<Integer> tmp, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        compares++;
        for (int i = 0; i < n1; ++i) {
            L[i] = tmp.get(l + i);
            compares++;
        }
        compares++;
        for (int j = 0; j < n2; ++j) {
            R[j] = tmp.get(m + 1 + j);
            compares++;
        }

        int i = 0, j = 0;

        int k = l;
        compares++;
        while (i < n1 && j < n2) {
            compares++;

            compares++;
            if (L[i] <= R[j]) {
                tmp.set(k, L[i]);
                i++;
            } else {
                tmp.set(k, R[j]);
                j++;
            }
            swaps++;
            k++;
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }

        compares++;
        while (i < n1) {
            compares++;

            swaps++;
            tmp.set(k, L[i]);
            i++;
            k++;
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }

        compares++;
        while (j < n2) {
            compares++;
            tmp.set(k, R[j]);
            j++;
            k++;
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }
    }
}

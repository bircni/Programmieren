package prog1.exercises.set09;
import java.util.ArrayList;

public class Heapsort extends Algorithm{

    @Override
    public String toString() {
        return "Heapsort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Heapsort(ArrayList<Integer> in, Main m) {
        this.m = m;
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));
        int n = tmp.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            compares++;
            heapify(tmp, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            compares++;
            int temp = tmp.get(0);
            tmp.set(0, tmp.get(i));
            tmp.set(i, temp);

            heapify(tmp, i, 0);

            swaps++;
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }

        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }

    void heapify(ArrayList<Integer> a, int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        compares++;
        if (left < n && a.get(left) > a.get(largest)) {
            largest = left;
        }
        compares++;
        if (right < n && a.get(right) > a.get(largest)) {
            largest = right;
        }
        compares++;
        if (largest != i) {
            int temp = a.get(i);
            a.set(i, a.get(largest));
            a.set(largest, temp);
            swaps++;
            heapify(a, n, largest);
        }
        steps.add(new ArrayList<>(a));
        stats.add(new Integer[]{swaps, compares});
    }
}

package prog1.exercises.set09;
import java.util.ArrayList;

public class Bubblesort extends Algorithm {

    @Override
    public String toString() {
        return "Bubblesort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Bubblesort(ArrayList<Integer> in, Main m) {
        this.m = m;
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));
        int n = steps.get(0).size();
        int temp = 0;
        for (int i = 0; i < n; i++) {
            compares++;
            for (int j = 1; j < (n - i); j++) {
                compares++;

                compares++;
                if (tmp.get(j - 1) > tmp.get(j)) {
                    temp = tmp.get(j - 1);
                    tmp.set(j - 1, tmp.get(j));
                    tmp.set(j, temp);
                    swaps++;
                    steps.add(new ArrayList<>(tmp));
                    stats.add(new Integer[]{swaps, compares});
                }
            }
            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }
        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }
}

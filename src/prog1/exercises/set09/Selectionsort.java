package prog1.exercises.set09;
import java.util.ArrayList;

public class Selectionsort extends Algorithm{
    @Override
    public String toString() {
        return "Quicksort";
    }

    @Override
    public void udpate() {
        geniters();
    }

    public Selectionsort(ArrayList<Integer> in, Main m) {
        this.m = m;
        steps.add(in);
        stats.add(new Integer[]{swaps, compares});
        new Thread(this::geniters).start();
    }

    void geniters() {
        ready = false;
        ArrayList<Integer> tmp = new ArrayList<>(steps.get(0));

        compares++;
        for (int i = 0; i < tmp.size() - 1; i++)
        {
            compares++;

            int index = i;

            compares++;
            for (int j = i + 1; j < tmp.size(); j++){
                compares++;

                compares++;
                if (tmp.get(j) < tmp.get(index)){
                    index = j;
                }
            }
            int smallerNumber = tmp.get(index);

            swaps++;
            tmp.set(index, tmp.get(i));
            tmp.set(i, smallerNumber);

            steps.add(new ArrayList<>(tmp));
            stats.add(new Integer[]{swaps, compares});
        }

        ready = true;
        m.update(true);
        System.out.println("Precalculation finished: " + steps.size() + " states");
    }
}

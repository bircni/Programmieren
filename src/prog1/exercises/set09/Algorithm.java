package prog1.exercises.set09;
import java.util.ArrayList;

public abstract class Algorithm {
    public ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
    int swaps = 0;
    int compares = 0;
    public ArrayList<Integer[]> stats = new ArrayList<>();
    boolean ready = false;
    Main m;

    public String toString() {
        return null;
    }

    public boolean isSorted(){
        return false;
    }

    public void udpate(){
    }

}

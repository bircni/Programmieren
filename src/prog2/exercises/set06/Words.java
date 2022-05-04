package prog2.exercises.set06;

public class Words implements Comparable<Words> {
    private final String inhalt;
    private int count;

    public Words(String i) {
        this.inhalt = i;
        count = 1;
    }

    public int getCount() {
        return count;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void increment() {
        count++;
    }

    @Override
    public int compareTo(Words o) {
        return o.getCount() - getCount();
    }
}

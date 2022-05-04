package prog2.help;

public class Texts implements Comparable<Texts> {
    private final String inhalt;
    private int anzahl;


    public Texts(String i) {
        this.inhalt = i;
        anzahl = 1;
    }

    public int getCount() {
        return anzahl;
    }

    public String getWord() {
        return inhalt;
    }

    public void counterplus() {
        anzahl++;
    }

    @Override
    public int compareTo(Texts o) {
        return o.getCount() - getCount();
    }
}

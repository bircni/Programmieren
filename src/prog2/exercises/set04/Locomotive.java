package prog2.exercises.set04;

public class Locomotive {
    private final int length;
    private final int type;
    private Car first;

    public Locomotive(int länge, int typ) {
        this.length = länge;
        this.type = typ;
    }

    public Car setFirst(Car f) {
        Car old = first;
        first = f;
        return old;
    }

    public Car getFirst() {
        return first;
    }

    public Car get(int i) {
        if (i == 0) return first;
        else return first.get(i - 1);
    }

    public void insertAfter(Car c, int i) {
        if (i == -1) {
            c.setNext(this.first);
            this.first = c;
        } else first.insertAfter(c, i - 1);
    }

    @Override
    public String toString() {
        return "Locomotive (" + length + ", " + type + ")" + (first != null ? ", " + first : "");
    }

    public int getNumberOfCars() {
        if (first == null) return 0;
        else return first.getNumberOfCars();
    }

    public void push_back(Car w) {
        if (first == null) first = w;
        else first.push_back(w);
    }
}

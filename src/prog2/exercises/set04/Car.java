package prog2.exercises.set04;

import java.util.Objects;

public class Car {
    private final int length;
    private final int capacity;
    private Car next;

    public Car(int length, int capacity) {
        this.length = length;
        this.capacity = capacity;
    }

    public Car getNext() {
        return next;
    }

    public Car setNext(Car next) {
        assert !Objects.equals(this, next);
        Car old = this.next;
        this.next = next;
        return old;
    }

    public int sumPassengers() {
        return capacity + (next != null ? next.sumPassengers() : 0);
    }

    public int sumLength() {
        return length + (next != null ? next.sumLength() : 0);
    }

    public void insertAfter(Car w, int i) {
        if (i == 0) {
            w.next = this.next;
            this.next = w;
        } else next.insertAfter(w, i - 1);
    }

    public Car get(int i) {
        if (i == 0) return next;
        else return next.get(i - 1);
    }

    @Override
    public String toString() {
        return "Car (" + length + ", " + capacity + ")" + (next != null ? ", " + next : "");
    }

    public int getNumberOfCars() {
        if (next == null) return 1;
        else return next.getNumberOfCars() + 1;
    }

    public void push_back(Car w) {
        if (next == null) next = w;
        else next.push_back(w);
    }
}

package prog2.exercises.set04;

public class Train {
    public Locomotive locomotive;

    public Train(Locomotive lokomotive) {
        this.locomotive = lokomotive;
    }

    public boolean hasCars() {
        return locomotive != null;
    }

    public Train insertAfter(Car w, int pos) {
        locomotive.insertAfter(w, pos);
        return this;
    }

    public Train push_back(Car w) {
        locomotive.push_back(w);
        return this;
    }

    public int getPassengers() {
        return locomotive.getFirst().sumPassengers();
    }

    public int getLength() {
        return locomotive.getFirst().sumLength();
    }

    public int getNumberOfCars() {
        return locomotive.getNumberOfCars();
    }

    public Car removeFirst() {
        if (locomotive.getFirst() == null) return null;
        else {
            return locomotive.getFirst().setNext(locomotive.getFirst().getNext());
        }
    }

    public void relink(Train t) {
        while (t.hasCars()) this.push_back(t.removeFirst());
    }

    public Car get(int i) {
        return locomotive.get(i);
    }

    public Train reverse() {
        if (this.hasCars()) {
            Car prev = null, curr = this.locomotive.getFirst(), next;
            while (curr != null) {
                next = curr.getNext();
                curr.setNext(prev);
                prev = curr;
                curr = next;
            }
            this.locomotive.setFirst(prev);
        }
        return this;
    }


    @Override
    public String toString() {
        return String.format("Train with length %d and capacity %d\n%s",
                this.getLength(),
                this.getPassengers(),
                locomotive.toString()
        );
    }
}

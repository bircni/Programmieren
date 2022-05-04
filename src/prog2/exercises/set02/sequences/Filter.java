package prog2.exercises.set02.sequences;

public abstract class Filter<T> extends Sequence<T> {
    Sequence<T> base_sq;

    public Filter(Sequence<T> s) {
        base_sq = s;
    }

    public abstract boolean testPassCondition(T element);

    public boolean hasNext() {
        return base_sq.hasNext();
    }

    public T nextElement() {
        T t = base_sq.nextElement();
        while (!testPassCondition(t)) {
            t = base_sq.nextElement();
        }
        return t;
    }
}

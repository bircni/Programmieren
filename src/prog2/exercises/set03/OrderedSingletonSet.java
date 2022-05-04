package prog2.exercises.set03;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class OrderedSingletonSet<T extends Comparable<T>> implements Comparable<OrderedSingletonSet<T>>, Collection<T> {
    public T fst;
    public T snd;

    public OrderedSingletonSet(T fst, T snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public T not(T o) {
        return fst.equals(o) ? snd : snd.equals(o) ? fst : null;
    }

    @Override
    public String toString() {
        return String.format("OrderedSingletonSet<%s>(%s, %s)", fst.getClass().getSimpleName(), fst, snd);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return o.equals(fst) || o.equals(snd);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSet<>(Set.of(fst, snd)).iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[]{fst, snd};
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return new HashSet<>(Set.of(fst, snd)).toArray(a);
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (((OrderedSingletonSet<?>) o).fst.getClass() != fst.getClass()) return false;
        return containsAll((OrderedSingletonSet<?>) o);
    }

    @Override
    public int hashCode() {
        return fst.hashCode() * snd.hashCode();
    }

    @Override
    public int compareTo(OrderedSingletonSet<T> o) {
        return fst.compareTo(o.fst) == 0 ? snd.compareTo(o.snd) : fst.compareTo(o.fst);
    }
}

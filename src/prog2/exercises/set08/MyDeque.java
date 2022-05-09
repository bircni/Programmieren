package prog2.exercises.set08;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyDeque<T> implements Iterable<T> {
    private Object[] content;

    public MyDeque(int capacity) {
        this.content = new Object[capacity];
    }

    public boolean isEmpty() {
        return Arrays.stream(content).allMatch(Objects::isNull);
    }

    public boolean isFull() {
        return Arrays.stream(content).allMatch(Objects::nonNull);
    }

    public int size() {
        return (int) Arrays.stream(content).filter(Objects::nonNull).count();
    }

    public int capacity() {
        return content.length;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) content[i];
    }

    public void addFirst(T elem) {
        content = Stream.of(
                Stream.of(elem),
                Arrays.stream(content)
        ).flatMap(Function.identity()).toArray();
    }

    @SuppressWarnings("unchecked")
    public T getFirst() {
        return (T) content[0];
    }

    public T removeFirst() {
        T tmp = getFirst();
        content = Stream.of(
                Arrays.stream(content).skip(1),
                Stream.of((T) null)
        ).flatMap(Function.identity()).toArray();
        return tmp;
    }

    public void addLast(T elem) {
        content[this.size()] = elem;
    }

    @SuppressWarnings("unchecked")
    public T getLast() {
        return (T) content[this.size() - 1];
    }

    public T removeLast() {
        T tmp = getLast();
        content[this.size() - 1] = null;
        return tmp;
    }

    public String toString() {
        return "[" +
                Arrays.stream(content).
                        map(x -> x == null ? "null" : x.toString())
                        .map(x -> String.format("%5s", x))
                        .collect(Collectors.joining(", "))
                + "]";
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(content).limit(this.size()).map(x -> (T) x).iterator();
    }

    public Iterator<T> iteratorNestedClass() {
        class internalIterator implements Iterator<T> {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                return (T) content[index++];
            }
        }
        return new internalIterator();
    }
}

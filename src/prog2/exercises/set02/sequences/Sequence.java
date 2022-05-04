package prog2.exercises.set02.sequences;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Sequence<T> {
    public abstract boolean hasNext();

    public abstract T nextElement();

    public String toString(int n) {
        return IntStream.range(0, n).mapToObj(x -> hasNext() ? nextElement().toString() + (x == n - 1 ? ", .." : "") : null).filter(Objects::nonNull).collect(Collectors.joining(", ")) + ".";
    }

    public String toString() {
        return toString(10);
    }
}

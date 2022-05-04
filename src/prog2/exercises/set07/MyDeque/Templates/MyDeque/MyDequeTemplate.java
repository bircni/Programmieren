package prog2.exercises.set07.MyDeque.Templates.MyDeque;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException.MyDequeNotAllocatedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MyDequeTemplate<T> {
    boolean isEmpty();

    boolean isFull();

    int size();

    int capacity();

    @Nullable
    T get(int i) throws MyDequeNotAllocatedException;

    void addFirst(@NotNull T elem);

    @Nullable
    T getFirst() throws MyDequeNotAllocatedException;

    @Nullable
    T removeFirst();

    void addLast(@NotNull T elem);

    @Nullable
    T getLast();

    @Nullable
    T removeLast();
}

package prog2.exercises.set07.MyDeque.MyDequeImpl;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException.MyDequeNotAllocatedException;
import prog2.exercises.set07.MyDeque.InstanceFactories.MyDequeAllocationManager.MyDequeAllocationHolderInstanceFactory;
import prog2.exercises.set07.MyDeque.InstanceFactories.MyDequeContentHolder.MyDequeContentHolderInstanceFactory;
import prog2.exercises.set07.MyDeque.Templates.Instance.MyDeque.MyDequeInstanceTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyDeque<T> extends MyDequeInstanceTemplate<T> implements Iterable<T> {
    public MyDeque(int n) {
        this.capacity = n;
        this.myDequeAllocationHolder = new MyDequeAllocationHolderInstanceFactory().getInstance(n);
        this.myDequeContentHolder = new MyDequeContentHolderInstanceFactory<T>().getInstance(n, this.myDequeAllocationHolder);
    }

    @Override
    public boolean isEmpty() {
        return IntStream.range(0, this.capacity).noneMatch(this.myDequeAllocationHolder::isAllocated);
    }

    @Override
    public boolean isFull() {
        return IntStream.range(0, this.capacity).allMatch(this.myDequeAllocationHolder::isAllocated);
    }

    @Override
    public int size() {
        return (int) IntStream.range(0, this.capacity).filter(this.myDequeAllocationHolder::isAllocated).count();
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public @Nullable T get(int i) {
        if (!this.myDequeAllocationHolder.isAllocated(i)) throw new MyDequeNotAllocatedException();
        return this.myDequeContentHolder.getContent()[i];
    }

    @Override
    public void addFirst(@NotNull T elem) {
        this.myDequeContentHolder.shiftR();
        this.myDequeAllocationHolder.shiftR();

        this.myDequeAllocationHolder.alloc(0);
        this.myDequeContentHolder.getContent()[0] = elem;
    }

    @Override
    public @Nullable T getFirst() {
        return this.myDequeContentHolder.getContent()[
                IntStream.range(0, this.capacity)
                        .filter(this.myDequeAllocationHolder::isAllocated)
                        .findFirst()
                        .orElseThrow((Supplier<RuntimeException>) MyDequeNotAllocatedException::new)
                ];
    }

    @Override
    public @Nullable T removeFirst() {
        T t = this.myDequeContentHolder.getContent()[0];
        this.myDequeAllocationHolder.free(0);

        this.myDequeContentHolder.shiftL();
        this.myDequeAllocationHolder.shiftL();
        return t;
    }

    @Override
    public void addLast(@NotNull T elem) {
        this.myDequeAllocationHolder.alloc(this.size());
        this.myDequeContentHolder.getContent()[this.size() - 1] = elem;
    }

    @Override
    public @Nullable T getLast() {
        return this.myDequeContentHolder.getContent()[
                IntStream.range(0, this.capacity)
                        .filter(this.myDequeAllocationHolder::isAllocated)
                        .reduce((x, y) -> y)
                        .orElseThrow((Supplier<RuntimeException>) MyDequeNotAllocatedException::new)];
    }

    @Override
    public @Nullable T removeLast() {
        T t = this.myDequeContentHolder.getContent()[this.size() - 1];
        this.myDequeAllocationHolder.free(this.size() - 1);
        return t;
    }

    @NotNull
    @Override
    public String toString() {
        return "MyDeque [" +
                IntStream.range(0, this.size())
                        .filter(this.myDequeAllocationHolder::isAllocated)
                        .mapToObj(this::get)
                        .map(Objects::toString)
                        .collect(Collectors.joining(", "))
                + "]";
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return IntStream.range(0, this.capacity()).boxed().filter(this.myDequeAllocationHolder::isAllocated).map(i -> this.myDequeContentHolder.getContent()[i]).iterator();
    }
}

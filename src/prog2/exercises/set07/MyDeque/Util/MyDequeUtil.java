package prog2.exercises.set07.MyDeque.Util;

import prog2.exercises.set07.MyDeque.MyDequeImpl.MyDeque;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MyDequeUtil {
    @Contract(pure = true)
    public static <T> MyDeque<T> merge(@NotNull MyDeque<T> myDeque1, @NotNull MyDeque<T> myDeque2) {
        MyDeque<T> myDeque = new MyDeque<>(myDeque1.capacity() + myDeque2.capacity());
        for (T t : myDeque2)
            myDeque.addLast(t);
        for (T t : myDeque1)
            myDeque.addLast(t);
        return myDeque;
    }

    @Contract(pure = true)
    public static <T extends Comparable<T>> MyDeque<T> selectLess(@NotNull MyDeque<T> myDeque1, @NotNull T elem) {
        MyDeque<T> myDeque = new MyDeque<>(myDeque1.capacity());
        for (T t : myDeque1) {
            if (t.compareTo(elem) < 0) myDeque.addLast(t);
        }
        return myDeque;
    }
}

package prog2.exercises.set07.MyDeque.InstanceFactories.MyDequeContentHolder;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeShiftException.MyDequeInvalidShiftException;
import prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers.MyDequeAllocationHolder;
import prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers.MyDequeContentHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public class MyDequeContentHolderInstanceFactory<T> {

    public MyDequeContentHolder<T> getInstance(int n, MyDequeAllocationHolder myDequeAllocationHolder) {
        return new MyDequeContentHolder<>(n, myDequeAllocationHolder) {
            @Override
            @SuppressWarnings("unchecked")
            @Contract(pure = true)
            public T[] getContent() {
                return (T[]) this.content;
            }

            @Override
            public void setContent(@NotNull T[] t) {
                this.content = t;
            }

            @Override
            public void shiftR() throws MyDequeInvalidShiftException {
                if (myDequeAllocationHolder.isAllocated(this.content.length - 1))
                    throw new MyDequeInvalidShiftException();
                this.content = Stream.concat(Stream.of(new Object[1]), Arrays.stream(this.content).limit(this.content.length - 1)).toArray();
            }

            @Override
            public void shiftL() {
                if (myDequeAllocationHolder.isAllocated(0))
                    throw new MyDequeInvalidShiftException();
                this.content = Stream.concat(Arrays.stream(this.content).skip(1), Stream.of(new Object[1])).toArray();
            }
        };
    }
}

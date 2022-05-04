package prog2.exercises.set07.MyDeque.InstanceFactories.MyDequeAllocationManager;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationAllocationException.MyDequeAllocationDoubleAllocationException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationAllocationException.MyDequeAllocationInvalidAllocationException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException.MyDequeAllocationDoubleFreeException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException.MyDequeAllocationInvalidFreeException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException.MyDequeAllocationOutOfBoundQueryException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeShiftException.MyDequeInvalidShiftException;
import prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers.MyDequeAllocationHolder;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.stream.Stream;

public class MyDequeAllocationHolderInstanceFactory {
    public MyDequeAllocationHolder getInstance(int n) {
        return new MyDequeAllocationHolder(n) {
            @Override
            public void alloc(int n) throws MyDequeAllocationInvalidAllocationException, MyDequeAllocationDoubleAllocationException {
                if (n >= this.allocationTable.length) throw new MyDequeAllocationInvalidAllocationException();
                if (this.allocationTable[n]) throw new MyDequeAllocationDoubleAllocationException();
                this.allocationTable[n] = true;
            }

            @Override
            public void free(int n) throws MyDequeAllocationDoubleFreeException, MyDequeAllocationInvalidFreeException {
                if (n >= this.allocationTable.length) throw new MyDequeAllocationInvalidFreeException();
                if (!this.allocationTable[n]) throw new MyDequeAllocationDoubleFreeException();
                this.allocationTable[n] = false;
            }

            @Contract(pure = true)
            @Override
            public boolean isAllocated(int n) throws MyDequeAllocationOutOfBoundQueryException {
                if (n >= this.allocationTable.length) throw new MyDequeAllocationOutOfBoundQueryException();
                return this.allocationTable[n];
            }

            @Override
            public void shiftR() throws MyDequeInvalidShiftException {
                if (this.isAllocated(this.allocationTable.length - 1))
                    throw new MyDequeInvalidShiftException();
                this.allocationTable = Stream.concat(Stream.of(new Boolean[]{Boolean.FALSE}), Arrays.stream(this.allocationTable).limit(this.allocationTable.length - 1)).toArray(Boolean[]::new);
            }

            @Override
            public void shiftL() {
                if (this.isAllocated(0))
                    throw new MyDequeInvalidShiftException();
                this.allocationTable = Stream.concat(Arrays.stream(this.allocationTable).skip(1), Stream.of(new Boolean[]{Boolean.FALSE})).toArray(Boolean[]::new);
            }
        };
    }
}

package prog2.exercises.set07.MyDeque.Templates.MyDequeAllocationManager;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationAllocationException.MyDequeAllocationDoubleAllocationException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationAllocationException.MyDequeAllocationInvalidAllocationException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException.MyDequeAllocationDoubleFreeException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException.MyDequeAllocationInvalidFreeException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException.MyDequeAllocationOutOfBoundQueryException;
import prog2.exercises.set07.MyDeque.Exceptions.MyDequeShiftException.MyDequeInvalidShiftException;

public interface MyDequeAllocationHolderTemplate {
    void alloc(int n) throws MyDequeAllocationInvalidAllocationException, MyDequeAllocationDoubleAllocationException;

    void free(int n) throws MyDequeAllocationInvalidFreeException, MyDequeAllocationDoubleFreeException;

    boolean isAllocated(int n) throws MyDequeAllocationOutOfBoundQueryException;

    void shiftR() throws MyDequeInvalidShiftException;

    void shiftL() throws MyDequeInvalidShiftException;
}

package prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationAllocationException;

public class MyDequeAllocationDoubleAllocationException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Tried to allocate same Object twice!";
    }
}

package prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException;

public class MyDequeAllocationDoubleFreeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Tried to free same Object twice!";
    }
}

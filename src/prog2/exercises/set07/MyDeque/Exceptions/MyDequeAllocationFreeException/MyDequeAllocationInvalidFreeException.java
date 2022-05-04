package prog2.exercises.set07.MyDeque.Exceptions.MyDequeAllocationFreeException;

public class MyDequeAllocationInvalidFreeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Tried to free non-allocated Object!";
    }
}

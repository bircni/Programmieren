package prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException;

public class MyDequeNotAllocatedException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Queried non-allocated Index";
    }
}

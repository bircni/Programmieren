package prog2.exercises.set07.MyDeque.Exceptions.MyDequeQueryException;

public class MyDequeAllocationOutOfBoundQueryException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid index";
    }
}

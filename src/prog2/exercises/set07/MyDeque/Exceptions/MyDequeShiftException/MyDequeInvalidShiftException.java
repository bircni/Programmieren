package prog2.exercises.set07.MyDeque.Exceptions.MyDequeShiftException;

public class MyDequeInvalidShiftException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid shift!";
    }
}

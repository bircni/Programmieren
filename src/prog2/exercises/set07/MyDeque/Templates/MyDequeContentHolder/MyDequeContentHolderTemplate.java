package prog2.exercises.set07.MyDeque.Templates.MyDequeContentHolder;

import prog2.exercises.set07.MyDeque.Exceptions.MyDequeShiftException.MyDequeInvalidShiftException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MyDequeContentHolderTemplate<T> {
    @Nullable
    @Contract(pure = true)
    T[] getContent();

    void setContent(@NotNull T[] t);

    void shiftR() throws MyDequeInvalidShiftException;

    void shiftL() throws MyDequeInvalidShiftException;
}

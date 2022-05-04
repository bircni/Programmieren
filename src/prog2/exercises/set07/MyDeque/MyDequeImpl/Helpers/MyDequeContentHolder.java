package prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers;

import prog2.exercises.set07.MyDeque.Templates.MyDequeContentHolder.MyDequeContentHolderTemplate;
import org.jetbrains.annotations.Nullable;

public abstract class MyDequeContentHolder<T> implements MyDequeContentHolderTemplate<T> {
    protected final MyDequeAllocationHolder allocationManager;
    @Nullable
    protected Object[] content;

    public MyDequeContentHolder(int n, MyDequeAllocationHolder allocationManager) {
        this.content = new Object[n];
        this.allocationManager = allocationManager;
    }
}

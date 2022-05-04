package prog2.exercises.set07.MyDeque.Templates.Instance.MyDeque;

import prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers.MyDequeAllocationHolder;
import prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers.MyDequeContentHolder;
import prog2.exercises.set07.MyDeque.Templates.MyDeque.MyDequeTemplate;

public abstract class MyDequeInstanceTemplate<T> implements MyDequeTemplate<T> {
    protected int capacity;
    protected MyDequeContentHolder<T> myDequeContentHolder;
    protected MyDequeAllocationHolder myDequeAllocationHolder;

    @Override
    abstract public String toString();
}

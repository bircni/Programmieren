package prog2.exercises.set07.MyDeque.MyDequeImpl.Helpers;

import prog2.exercises.set07.MyDeque.Templates.MyDequeAllocationManager.MyDequeAllocationHolderTemplate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class MyDequeAllocationHolder implements MyDequeAllocationHolderTemplate {
    protected Boolean[] allocationTable;

    protected MyDequeAllocationHolder(int n) {
        this.allocationTable = IntStream.range(0, n).boxed().map(x -> Boolean.FALSE).collect(Collectors.toList()).toArray(Boolean[]::new);
    }
}

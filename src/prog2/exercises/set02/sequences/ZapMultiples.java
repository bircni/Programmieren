package prog2.exercises.set02.sequences;

public class ZapMultiples extends Filter<Integer> {
    int base;

    public ZapMultiples(Sequence<Integer> s, int base) {
        super(s);
        this.base = base;
    }

    @Override
    public boolean testPassCondition(Integer element) {
        return element % base != 0;
    }
}

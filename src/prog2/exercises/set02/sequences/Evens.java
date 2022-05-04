package prog2.exercises.set02.sequences;

public class Evens extends Filter<Integer> {

    public Evens(Sequence<Integer> s) {
        super(s);
    }

    @Override
    public boolean testPassCondition(Integer element) {
        return element % 2 == 0;
    }
}

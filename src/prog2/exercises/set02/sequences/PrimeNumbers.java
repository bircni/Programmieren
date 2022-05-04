package prog2.exercises.set02.sequences;

public class PrimeNumbers extends Sequence<Integer> {
    Sequence<Integer> base_sq = new Naturals();

    public PrimeNumbers(){
        base_sq.nextElement();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer nextElement() {
        int n = base_sq.nextElement();
        base_sq = new ZapMultiples(base_sq, n);
        return n;
    }
}

package prog2.exercises.set02.sequences;

public class Range extends Sequence<Integer> {

    int state, end, step;

    public Range(int start_inclusive, int end_inclusive) {
        state = start_inclusive - 1;
        end = end_inclusive;
        step = 1;
    }

    public Range(int start_inclusive, int end_exclusive, int step) {
        state = start_inclusive;
        end = end_exclusive;
        this.step = step;
    }

    @Override
    public boolean hasNext() {
        return state < end;
    }

    @Override
    public Integer nextElement() {
        return state <= end ? state += step : null;
    }
}

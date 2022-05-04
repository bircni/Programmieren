package prog2.exercises.set02.sequences;

//import java.util.stream.Collectors;
//import java.util.stream.IntStream;

public class Naturals extends Sequence<Integer> {

    int state = 0;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer nextElement() {
        return ++state;
    }
}

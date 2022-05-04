package prog2.exercises.set02;

import prog2.exercises.set02.sequences.*;

public class SequenceTest {
    public static void main(String[] args) {
        System.out.println(new Naturals());
        System.out.println(new Range(7, 14));
        System.out.println(new Evens(new Range(7, 14)));
        System.out.println(new ZapMultiples(new Range(7, 14), 3));
        System.out.println(new PrimeNumbers());
    }
}

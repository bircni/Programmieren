package prog1.exercises.set08;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.IntStream;

//DAS MIT DEM PAINT TOOL WAR ZU KOMPLIZIERT DESWEGEN SELBER GEMACHT IN DER KLASSE MYPAINTER
public class TestRegression {
    public static void main(String[] a) {
        DecimalFormat df = new DecimalFormat("#");

        Point[] p = new Point[]{
                new Point(0, 15),
                new Point(1, 37),
                new Point(2, 52),
                new Point(3, 59),
                new Point(4, 83),
                new Point(5, 92)
        };

        Regression r = new Regression(() -> Arrays.stream(p));

        for (Point k : p) {
            System.out.println("Verkäufe im Monat " + df.format(k.getX() + 1) + ": " + df.format(k.getY()));
        }

        IntStream.range(0, 6).forEach(i -> System.out.println("Geschätzte Verkäufe in Monat " + df.format(i + 1 + 6) + ": " + df.format(r.point(i + 6).getY())));

        System.out.println(r);
        MyPainter mp = new MyPainter(() -> Arrays.stream(p), r.getM(), r.getC());
    }
}

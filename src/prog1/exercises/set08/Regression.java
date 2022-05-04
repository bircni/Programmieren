package prog1.exercises.set08;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Regression {
    private final double m;
    private final double c;

    public Regression(Supplier<Stream<Point>> ps) {
        m = ((ps.get().count() * ps.get().mapToDouble(i -> i.getX() * i.getY()).sum())
                - (ps.get().mapToDouble(Point::getX).sum()
                * ps.get().mapToDouble(Point::getY).sum()))
                / ((ps.get().count() * ps.get().mapToDouble(i -> i.getX() * i.getX()).sum())
                - (ps.get().mapToDouble(Point::getX).sum() * ps.get().mapToDouble(Point::getX).sum()));

        c = (ps.get().mapToDouble(Point::getY).sum() - (m * ps.get().mapToDouble(Point::getX).sum())) / ps.get().count();
    }

    public static void main(String[] k) {
        Regression r = new Regression(() -> Arrays.stream(new Point[]{
                new Point(1, 1),
                new Point(2, 2)
        }));
        System.out.println(r);
        System.out.println(r.point(3));
    }

    public double getM() {
        return m;
    }

    public double getC() {
        return c;
    }

    public Point point(int x) {
        return new Point(x, m * x + c);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        return "y = " + df.format(m) + " * x + " + df.format(c);
    }
}

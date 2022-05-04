package prog1.exercises.set10;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Vector2D {
    double x;
    double y;

    public Vector2D(double[] ar) {
        x = ar[0];
        y = ar[1];
    }

    public Vector2D() {
    }

    public Vector2D(Object[] ar) {
        x = (double) ar[0];
        y = (double) ar[1];
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D a) {
        this.x = a.x;
        this.y = a.y;
    }

    public double[] da() {
        return new double[]{x, y};
    }

    private double get(int i) {
        return i == 0 ? x : y;
    }

    public Vector2D mult(double factor) {
        return new Vector2D(Arrays.stream(this.da()).map(i -> i * factor).toArray());
    }

    public Vector2D plus(Vector2D vec1) {
        return new Vector2D(IntStream.range(0, 2).mapToDouble(i -> vec1.get(i) + this.get(i)).toArray());
    }

    public Vector2D minus(Vector2D vec1) {
        return new Vector2D(IntStream.range(0, 2).mapToDouble(i -> this.get(i) - vec1.get(i)).toArray());
    }

    public double vlength() {
        return Math.sqrt(Arrays.stream(this.da()).map(i -> Math.pow(i, 2)).sum());
    }

    public Vector2D rotate2d(int deg) {
        return new Vector2D(
                Math.cos(Math.toRadians(deg)) * this.x - Math.sin(Math.toRadians(deg)) * this.y,
                Math.sin(Math.toRadians(deg)) * this.x + Math.cos(Math.toRadians(deg)) * this.y
        );
    }

    public Vector2D normalize() {
        return this.mult(1 / this.vlength());
    }

    public Vector2D translate(int width, int height, int margin, int maxx, int maxy) {
        Vector2D k = new Vector2D();

        k.x = margin + (x / maxx * (width - (2 * margin)));
        k.y = height - (margin + (y / maxy * (height - (2 * margin))));

        return k;
    }

    public String toString(){
        return x + ", " + y;
    }
}
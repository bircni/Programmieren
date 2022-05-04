package prog1.exercises.set10;

public class Triangle extends FractalWorthyShape {
    public Triangle(Vector2D A, Vector2D B, int alpha, int gamma) {
        if (gamma == 90) {
            points.put("A", A);
            points.put("B", B);
            points.put("C", A.plus(B.minus(A).rotate2d(alpha).mult(Math.cos(Math.toRadians(alpha)))));
        }
    }
}

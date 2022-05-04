package prog1.exercises.set10;

public class FourPointPolygon extends FractalWorthyShape {
    public FourPointPolygon(Vector2D a, Vector2D b, Vector2D c, Vector2D d){
        points.put("A", a);
        points.put("B", b);
        points.put("C", c);
        points.put("D", d);
    }

    public FourPointPolygon(Vector2D a, Vector2D b){
        points.put("A", a);
        points.put("B", b);
        points.put("C", b.plus(b.minus(a).rotate2d(90)));
        points.put("D", a.plus(b.minus(a).rotate2d(90)));
    }
}

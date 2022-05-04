package prog1.exercises.set10;

import rl.prog1.util.painttool.PaintTool;

import java.awt.*;

public class SimpleFractal2 extends Fractal<Triangle> {
    public int added = 1;

    public SimpleFractal2(Vector2D a, Vector2D b, int angle) {
        this.shapes.add(new Triangle(a, b, angle, 90));
        this.angle = angle;
    }

    @Override
    void iterate() {
        int k = shapes.size();

        for (int i = k - added; i < k; i++) {
            shapes.add(new Triangle(
                    shapes.get(i).points.get("A"),
                    shapes.get(i).points.get("C"),
                    angle,
                    90
            ));

            shapes.add(new Triangle(
                    shapes.get(i).points.get("C"),
                    shapes.get(i).points.get("B"),
                    angle,
                    90
            ));
            added++;
        }
    }

    @Override
    void draw(PaintTool pt, int dim, boolean force) {
        for (int i = shapes.size() - (force ? shapes.size() : added); i < shapes.size(); i++) {
            Triangle t = shapes.get(i);

            Graphics2D g = (Graphics2D) pt.getCanvas().getGraphics();
            g.setColor((Math.log(added) / Math.log(2)) % 2 == 0 ? new Color(0x1A7DFF) : new Color(0xFF1A6B));

            g.fillPolygon(new Polygon(
                    new int[]{
                            (int) t.points.get("A").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                            (int) t.points.get("B").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                            (int) t.points.get("C").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x
                    }, new int[]{
                    (int) t.points.get("A").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
                    (int) t.points.get("B").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
                    (int) t.points.get("C").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
            }, 3));
        }
    }
}

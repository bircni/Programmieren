package prog1.exercises.set10;

import rl.prog1.util.painttool.PaintTool;

import java.awt.*;
import java.util.ArrayList;

public class PythagorasTree extends Fractal<FourPointPolygon> {
    public int added = 1;

    public PythagorasTree(Vector2D a, Vector2D b, int angle) {
        shapes.add(new FourPointPolygon(a, b));
        this.angle = angle;
    }

    @Override
    void iterate() {
        int k = shapes.size();


        for (FourPointPolygon r : new ArrayList<>(shapes)) {

            if (r.points.get("B") == r.points.get("C")) {
                continue;
            }

            FourPointPolygon tri = new FourPointPolygon(r.points.get("D"), r.points.get("C"), r.points.get("C"),
                    r.points.get("D").plus(r.points.get("C").minus(r.points.get("D")).rotate2d(angle).mult(Math.cos(Math.toRadians(angle)))));
            shapes.add(tri);

            shapes.add(new FourPointPolygon(tri.points.get("A"), tri.points.get("D")));

            shapes.add(new FourPointPolygon(tri.points.get("D"), tri.points.get("C")));

            added++;
        }
    }

    @Override
    void draw(PaintTool pt, int dim, boolean force) {
        for (int i = shapes.size() - (force ? shapes.size() : added); i < shapes.size(); i++) {
            FourPointPolygon t = shapes.get(i);

            if (t.points.get("B") == t.points.get("C")) {
                continue;
            }

            Graphics2D g = (Graphics2D) pt.getCanvas().getGraphics();

            float[] from = new float[3];
            float[] to = new float[3];
            Color.RGBtoHSB(new Color(0x3B2F00).getRed(), new Color(0x3B2F00).getGreen(), new Color(0x3B2F00).getBlue(), from);
            Color.RGBtoHSB(new Color(0x008D08).getRed(), new Color(0x008D08).getGreen(), new Color(0x008D08).getBlue(), to);

            int n = 15;
            double percent = Math.floor((Math.log(shapes.indexOf(t)) / Math.log(3)));

            float[] col = new float[3];
            col[0] = (float) (from[0] + (percent * (to[0] - from[0]) / n));
            col[1] = (float) (from[1] + (percent * (to[1] - from[1]) / n));
            col[2] = (float) (from[2] + (percent * (to[2] - from[2]) / n));

            g.setColor(Color.getHSBColor(col[0], col[1], col[2]));
            g.fillPolygon(new Polygon(
                    new int[]{
                            (int) t.points.get("A").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                            (int) t.points.get("B").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                            (int) t.points.get("C").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                            (int) t.points.get("D").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).x,
                    }, new int[]{
                    (int) t.points.get("A").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
                    (int) t.points.get("B").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
                    (int) t.points.get("C").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
                    (int) t.points.get("D").translate(dim, dim, Main.margin, Main.maxx, Main.maxy).y,
            }, 4));

            g.setColor(Color.black);
        }
    }
}

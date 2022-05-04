package prog1.exercises.set10;

import rl.prog1.util.painttool.PaintTool;

import java.util.ArrayList;

public abstract class Fractal<T extends FractalWorthyShape> {
    protected int angle;
    protected int added;
    protected ArrayList<T> shapes = new ArrayList<>();

    abstract void iterate();

    abstract void draw(PaintTool pt, int dim, boolean force);
}

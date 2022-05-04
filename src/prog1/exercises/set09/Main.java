package prog1.exercises.set09;
import rl.prog1.util.painttool.PaintTool;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Main {
    Algorithm active;
    private final ArrayList<Integer> data = new ArrayList<>(IntStream.range(0, 1500).boxed().toList());
    private final Drawer d;
    private int step = 0;
    private final PaintTool pt;
    private boolean play = false;

    public static void main(String[] ignored) {
        new Main();
    }

    public Main() {
        SortingToolController stc = new SortingToolController(this);
        pt = new PaintTool(stc);

        Collections.shuffle(data);
        active = new Quicksort(data, this);
        d = new Drawer(pt);

        pt.getCanvas().getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                update(true);
            }
        });
        pt.setAutoUpdate(true);
        pt.setVisible(true);
    }

    public void setAlgorithm(Algorithm a) {
        play = false;
        step = 0;
        this.active = a;
        update(true);
    }

    public ArrayList<Integer> getData() {
        return new ArrayList<>(data);
    }

    public void shuffle() {
        play = false;
        step = 0;
        Collections.shuffle(data);
        this.active.steps = new ArrayList<>();
        this.active.steps.add(data);
        this.active.udpate();
        this.update(true);
    }

    public void step() {
        update(false);
        step++;
    }

    public void update(boolean force) {
        if(step > active.steps.size() - 1){
            step = active.steps.size() - 1;
        }
        d.update(active.steps.get(step), active.steps.get(active.steps.size() - 1), pt.getCanvas().getWidth(),
                pt.getCanvas().getHeight(), active.stats.get(step), active.toString(), play, force, active.ready);
    }

    public void play() {
        play = true;
        for (int i = step; i < active.steps.size(); i++) {
            if (!play) break;
            step();
            update(false);
        }
        play = false;
        update(false);
    }

    public void stop() {
        play = false;
        update(false);
    }
}

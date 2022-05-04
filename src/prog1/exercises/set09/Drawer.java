package prog1.exercises.set09;
import rl.prog1.util.painttool.PaintTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Drawer {
    BufferedImage bi;
    PaintTool pt;
    final int margin = 20;
    private ArrayList<Integer> old;

    public Drawer(PaintTool pt) {
        this.pt = pt;
        bi = new BufferedImage(pt.getCanvas().getWidth(), pt.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
        old = new ArrayList<>();
    }

    void pause(int width, int height){

    }

    void update(ArrayList<Integer> data, ArrayList<Integer> end, int width, int height, Integer[] stats, String m, boolean running, boolean force, boolean ready) {
        if (old.size() == 0) {
            for (Integer ignored : data) {
                old.add(Integer.MAX_VALUE);
            }
        }

        if (force) {
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }

        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(old.get(i)) && !force) {
                continue;
            }

            g.setColor(new Color(Color.HSBtoRGB((float) (end.indexOf(data.get(i)) / (double) (data.size() - 1)), 1, 1)));

            g.clearRect(margin + (i * (width - (2 * margin)) / data.size()),
                    0,
                    (int) Math.max(1, Math.ceil((width - (2 * margin)) / (float) data.size())),
                    height
            );
            g.fillRect(
                    margin + (i * (width - (2 * margin)) / data.size()),
                    height - (1 + data.get(i) * (height - (2 * margin)) / data.size()) - margin,
                    (int) Math.max(1, Math.ceil((width - (2 * margin)) / (float) data.size())),
                    1 + data.get(i) * (height - (2 * margin)) / data.size()
            );
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 450, (int) (1.5 * margin));
        g.setColor(running ? Color.green : Color.RED);
        g.drawString((ready ? "READY!    " : "CALCULATING...   ") + m + "  -  " + (running ? "RUNNING" : "STOPPED") + "    Swaps: " + stats[0] + "    Compares: " + stats[1], margin, margin);

        pt.getCanvas().getGraphics().drawImage(bi, 0, 0, null);

        old = new ArrayList<>(data);
    }
}

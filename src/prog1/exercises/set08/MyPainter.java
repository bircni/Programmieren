package prog1.exercises.set08;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings("SpellCheckingInspection")
public class MyPainter extends JPanel {
    final double m;
    final double c;
    final int dim = 6;
    final int margin = 10;
    boolean draw_reg = false;
    int verfx, verfy;
    Supplier<Stream<Point>> ps;

    JFrame f = new JFrame("Linear regression");
    JButton plr = new JButton("Perform linear regression");
    JToolBar jtb = new JToolBar();

    public MyPainter(Supplier<Stream<Point>> ps, double m, double c) {
        this.ps = ps;
        this.m = m;
        this.c = c;

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1000, 600);
        f.setLayout(new BorderLayout());

        jtb.addSeparator();
        jtb.add(plr);

        f.add(jtb, BorderLayout.NORTH);
        f.add(this, BorderLayout.CENTER);

        f.setVisible(true);
        this.requestFocus();

        plr.addActionListener(e -> {
            draw_reg = true;
            f.repaint();
            Arrays.stream(plr.getActionListeners()).forEach(plr::removeActionListener);
        });
    }

    public void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        verfx = (this.getWidth() - margin) / 2 - dim;
        verfy = (this.getHeight() - margin) / 2 - jtb.getHeight() - dim;

        g.drawLine(0, verfy + margin, this.getWidth(), verfy + margin);
        g.drawLine(verfx, 0, verfx, this.getHeight());

        g.setColor(new Color(0xFF0000));
        ps.get().forEach(i -> g.fillRect(
                (int) (verfx + ((i.getX() / ps.get().mapToDouble(Point::getX).max().orElse(0)) * verfx)) - dim / 2,
                (int) (verfy - ((i.getY() / ps.get().mapToDouble(Point::getY).max().orElse(0)) * verfy) + margin) - dim / 2,
                dim, dim)
        );

        if (draw_reg) {
            g.setColor(new Color(0x0000FF));
            g.drawLine(0,
                    (int) (verfy - (((m * -ps.get().mapToDouble(Point::getX).max().orElse(0) + c)
                            / ps.get().mapToDouble(Point::getY).max().orElse(0)) * verfy) + margin) - dim / 2,
                    this.getWidth(),
                    (int) (verfy - (((m * ps.get().mapToDouble(Point::getX).max().orElse(0) + c)
                            / ps.get().mapToDouble(Point::getY).max().orElse(0)) * verfy) + margin) - dim / 2
            );
        }
    }
}
package prog1.exercises.set10;

import rl.prog1.util.painttool.AbstractController;
import rl.prog1.util.painttool.PaintTool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static Fractal f;
    public static PaintTool pt;
    public static int angle = 35;
    public static final int margin = 20;
    public static final int maxx = 15;
    public static final int maxy = 15;
    public static final DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.GERMANY);
    public static final ThreadLocal<Thread> t = ThreadLocal.withInitial(Thread::new);

    public static void main(String[] ignored) {
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbols);

        f = new SimpleFractal1(new Vector2D(8, 6), new Vector2D(15, 6), angle);

        AbstractController ac = new AbstractController() {
            @Override
            public String getTitle() {
                return "Titel";
            }

            @Override
            public String[] getButtonNames() {
                return new String[]{
                        "iterate",
                        "Simple Fraktal 1",
                        "Simple Fraktal 2",
                        "Pythagoras Tree"
                };
            }

            @Override
            public void onButtonPressed(PaintTool ptool, int button) {
                switch (button) {
                    case 0 -> {
                        f.iterate();
                        f.draw(pt, Math.min(pt.getCanvas().getWidth(), pt.getCanvas().getHeight()), false);
                        System.out.println("Number of shapes: " + df.format(f.shapes.size()));
                    }
                    case 1 -> {
                        f = new SimpleFractal1(new Vector2D(8, 6), new Vector2D(15, 6), angle);
                        pt.getCanvas().getGraphics().clearRect(0, 0, pt.getCanvas().getWidth(), pt.getCanvas().getHeight());
                        f.draw(pt, Math.min(pt.getCanvas().getWidth(), pt.getCanvas().getHeight()), true);
                    }
                    case 2 -> {
                        f = new SimpleFractal2(new Vector2D(8, 6), new Vector2D(15, 6), angle);
                        pt.getCanvas().getGraphics().clearRect(0, 0, pt.getCanvas().getWidth(), pt.getCanvas().getHeight());
                        f.draw(pt, Math.min(pt.getCanvas().getWidth(), pt.getCanvas().getHeight()), true);
                    }
                    case 3 -> {
                        f = new PythagorasTree(new Vector2D(10, 3), new Vector2D(12, 3), angle);
                        pt.getCanvas().getGraphics().clearRect(0, 0, pt.getCanvas().getWidth(), pt.getCanvas().getHeight());
                        f.draw(pt, Math.min(pt.getCanvas().getWidth(), pt.getCanvas().getHeight()), true);
                    }
                }
            }
        };

        pt = new PaintTool(ac);

        JSpinner js = new JSpinner();
        js.setValue(35);
        js.setName("Angle");

        js.addChangeListener(e -> angle = (int) js.getValue());

        JLabel l = new JLabel("Angle in Degree");

        int n = ((JToolBar) (pt.getCanvas().getParent().getComponent(0))).getComponents().length;
        ((JToolBar) (pt.getCanvas().getParent().getComponent(0))).add(js, n-2);
        ((JToolBar) (pt.getCanvas().getParent().getComponent(0))).add(new JSeparator(), n-1);
        ((JToolBar) (pt.getCanvas().getParent().getComponent(0))).add(l, n);
        ((JToolBar) (pt.getCanvas().getParent().getComponent(0))).add(new JSeparator(), n+1);

        pt.getCanvas().getParent().addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                f.draw(pt, Math.min(pt.getCanvas().getWidth(), pt.getCanvas().getHeight()), true);
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });

        pt.setVisible(true);
        pt.getCanvas().getParent().getParent().getParent().getParent().setBounds(
                (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 600,
                (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 400,
                1200,
                800
        );
    }
}

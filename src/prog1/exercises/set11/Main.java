package prog1.exercises.set11;

import rl.prog1.util.painttool.AbstractController;
import rl.prog1.util.painttool.PaintTool;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Main extends AbstractController {

    static Thread anim;
    boolean first = true;

    public static void main(String[] args) {
        Main m = new Main();
        PaintTool pt = new PaintTool(m);

        pt.getCanvas().getParent().addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (anim != null) anim.interrupt();
                pt.getCanvas().getGraphics().clearRect(0, 0, pt.getCanvas().getWidth(), pt.getCanvas().getHeight());
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });

        pt.getCanvas().getParent().getParent().getParent().getParent().setBounds(
                (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 600,
                (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 400,
                1200,
                800
        );

        pt.setVisible(true);

    }

    void paintTree(PaintTool p) {
        play();

        BufferedImage img = new BufferedImage(p.getCanvas().getWidth(), p.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());

        int max = Math.min(p.getCanvas().getHeight(), p.getCanvas().getWidth());

        int offsetx = (p.getCanvas().getWidth() - max) / 2;
        int offsety = (p.getCanvas().getHeight() - max) / 2;

        g.setColor(new Color(0x0));
        g.drawRect(offsetx, offsety, max, max);

        g.setColor(new Color(0x503100));
        g.fillRect(offsetx + ((max - 50) / 2), offsety + ((max - 50)), 50, 50);

        g.setColor(new Color(0x00B100));

        Polygon polygon = new Polygon(new int[]{
                offsetx + max / 2, offsetx + (max / 2) - 300, offsetx + (max / 2) + 300
        }, new int[]{
                offsety, offsety + ((max - 50)), offsety + ((max - 50))
        }, 3);
        g.fillPolygon(polygon);

        int[][] kerzen = new int[2 << 4][2];
        int[] kerzen_alpha = new int[2 << 4];

        for (int i = 0; i < kerzen_alpha.length; i++) {
            kerzen_alpha[i] = new Random().nextInt(255);
        }

        for (int i = 0; i < (2 << 4); i++) {

            int x = offsetx + (int) Math.round(Math.random() * max);
            int y = offsety + (int) Math.round(Math.random() * max);

            if (polygon.contains(new Point(x, y))) {
                g.setColor(new Color(0xFD0000));
                g.fillRect(x, y, 30, 70);
                kerzen[i] = new int[]{x, y};
            }
        }

        p.getCanvas().getGraphics().drawImage(img, 0, 0, null);

        if (this.anim != null) anim.interrupt();
        anim = new Thread() {
            public void start() {
                while (!this.isInterrupted()) {
                    flicker(p, kerzen, kerzen_alpha, img);
                    try {
                        Thread.sleep(50);
                    } catch (Exception ignored) {
                    }
                }
            }
        };
        anim.start();
    }

    void flicker(PaintTool p, int[][] kerzen, int[] kerzen_alpha, BufferedImage img) {
        Graphics2D g = (Graphics2D) p.getCanvas().getGraphics();

        g.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
        g.drawImage(img, 0, 0, null);

        for (int i = 0; i < kerzen.length; i++) {
            kerzen_alpha[i] += 10;

            if (kerzen_alpha[i] > 255) {
                kerzen_alpha[i] = 0;
            }

            g.setColor(new Color(0xF1, 0xFF, 0x00, kerzen_alpha[i]));
            g.fillOval(kerzen[i][0], kerzen[i][1] - 35, 30, 30);

        }
    }

    void play() {
        if (!first) return;
        first = false;

        try {
            byte[] bytes = new byte[978690];

            Socket socket = new Socket("fabus1184.v6.army", 12345);

            DataInputStream dis = new DataInputStream(socket.getInputStream());

            dis.readFully(bytes);
            File tmp = File.createTempFile("tune", ".wav");
            Files.copy(new ByteArrayInputStream(bytes), tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println(tmp.getAbsolutePath());

            AudioInputStream ais = AudioSystem.getAudioInputStream(tmp.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle() {
        return "Xmas App";
    }

    @Override
    public String[] getButtonNames() {
        return new String[]{
                "Paint Tree",
        };
    }

    @Override
    public void onButtonPressed(PaintTool ptool, int button) {
        switch (button) {
            case 0 -> {
                paintTree(ptool);
                play();
            }
        }
    }

    @Override
    public void onMouseClick(PaintTool ptool, int x, int y, int mouseButton, boolean isShiftDown, boolean isControlDown) {
    }
}

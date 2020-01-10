package lab5.graphics;

import lab5.helpers.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JComponent implements IObserver {
    Document doc;
    boolean flag = false;

    DrawingFrame(Document doc) {
        super();

        this.doc = doc;

        this.setFocusable(true);
        this.setVisible(true);
        this.setEnabled(true);
        setPreferredSize(new Dimension(700, 700));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                flag = true;
            }

            public void mouseReleased(MouseEvent e) {
                flag = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (flag) {
                    doc.add(new Tuple<>(e.getX(), e.getY()));
                }
            }
        });
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.requestFocusInWindow();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        doc.drawLine(g2);
    }
}

package lab5.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    Document doc;
    public Frame(Document doc) {
        setPreferredSize(new Dimension(1200, 1200));

        this.doc = doc;
        DrawingFrame frame = new DrawingFrame(doc);
        doc.addObserver(frame);

        setMaximumSize(new Dimension(1200, 1200));
        setButtons();
        frame.setFocusable(true);
        frame.setEnabled(true);
        frame.grabFocus();
        pack();
        add(frame, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }



    private void setButtons() {
        JToolBar toolbar = new JToolBar();
        Action ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.save();
            }

        };
        JButton button = new JButton();
        ac.putValue(Action.NAME, "Save");
        button.setAction(ac);
        toolbar.add(button);
        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.clear();
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Clear");
        button.setAction(ac);
        toolbar.add(button);
        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setDrawingClass("L00001");
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Alpha");
        button.setAction(ac);
        toolbar.add(button);
        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setDrawingClass("L00010");
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Beta");
        button.setAction(ac);
        toolbar.add(button);
        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setDrawingClass("L00100");
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Gamma");
        button.setAction(ac);
        toolbar.add(button);

        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setDrawingClass("L01000");
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Sigma");
        button.setAction(ac);
        toolbar.add(button);

        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setDrawingClass("L10000");
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Epsilon");
        button.setAction(ac);
        toolbar.add(button);

        ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.predict();
            }
        };
        button = new JButton();
        ac.putValue(Action.NAME, "Predict");
        button.setAction(ac);
        toolbar.add(button);

        add(toolbar, BorderLayout.PAGE_START);
    }
}

package lab5;

import lab5.graphics.Document;
import lab5.graphics.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Document doc = new Document();
        SwingUtilities.invokeLater(() -> new Frame(doc));
    }
}

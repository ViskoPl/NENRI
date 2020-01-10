package lab5.graphics;

import lab5.helpers.Tuple;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public interface IDrawing {
    void save() throws IOException;
    void add(Tuple tup);
    void clear();
    void drawLine(Graphics2D g);
    ArrayList<Tuple> scale();
}

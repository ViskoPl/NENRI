package lab5;

import lab5.algorithms.BackPropagation;
import lab5.algorithms.IAlgorithm;
import lab5.graphics.Document;
import lab5.graphics.Frame;
import lab5.layers.HiddenLayer;
import lab5.layers.ILayer;
import lab5.layers.OutPutLayer;
import lab5.net.INN;
import lab5.net.NN;

import javax.swing.*;
import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
        ILayer hiddenLayer1 = new HiddenLayer(2, 40, 2);
        ILayer hiddenLayer2 = new HiddenLayer(2, 2, 5);
        ILayer outputLayer = new OutPutLayer(5, 2, 1);
        IAlgorithm alg = new BackPropagation();
        INN net = new NN(alg);
        net.addLayer(hiddenLayer1);
        net.addLayer(hiddenLayer2);
        net.addLayer(outputLayer);
        net.train(0.000001);
        Document doc = new Document(net);
        SwingUtilities.invokeLater(() -> new Frame(doc) );
    }

}

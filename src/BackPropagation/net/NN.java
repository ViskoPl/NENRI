package lab5.net;

import lab5.algorithms.IAlgorithm;
import lab5.helpers.Tuple;
import lab5.helpers.np;
import lab5.layers.HiddenLayer;
import lab5.layers.ILayer;
import lab5.layers.OutPutLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NN implements INN{

    private IAlgorithm alg;
    List<ILayer> layers = new ArrayList<>();
    final String[] drawingNames = {"Epsilon", "Sigma", "Gamma", "Beta", "Alpha"};

    public NN(IAlgorithm alg) {
        super();

        this.alg = alg;
    }

    @Override
    public void addLayer(ILayer layer) {
        layers.add(layer);
    }

    @Override
    public void removeLayer(int index) {
        layers.remove(index);
    }

    @Override
    public void train(double learningRate) throws IOException {
        alg.train(layers, learningRate);
    }

    @Override
    public void predict(List<Tuple> list) {
        double[][] X = new double[1][40];
        int index = 0;
        for (Tuple t: list) {
            X[0][index] = Double.parseDouble(t.getX().toString());
            index++;
            X[0][index] = Double.parseDouble(t.getY().toString());
            index++;
        }

        X = np.T(X);

        double[][] Z1 = np.add(np.dot(((HiddenLayer) layers.get(0)).getWeights(), X), ((HiddenLayer) layers.get(0)).getBias());
        double[][] A1 = np.sigmoid(Z1);

        //LAYER 2
        double[][] Z2 = np.add(np.dot(((HiddenLayer) layers.get(1)).getWeights(), A1), ((HiddenLayer) layers.get(1)).getBias());
        double[][] A2 = np.sigmoid(Z2);

        //Layer 3
        double[][] Z3 = np.add(np.dot(((OutPutLayer) layers.get(2)).getWeights(), A2), ((OutPutLayer) layers.get(2)).getBias());
        double[][] A3 = np.sigmoid(Z3);

        index = 0;
        System.out.println("=======");
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 5; i++) {
            System.out.println(A3[i][0]);
            if (A3[i][0] > max) {
                max = A3[i][0];
                index = i;
            }
        }



        System.out.println(drawingNames[index]);
    }
}

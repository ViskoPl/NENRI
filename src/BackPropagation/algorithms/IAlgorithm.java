package lab5.algorithms;

import lab5.layers.ILayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IAlgorithm {
    void train(List<ILayer> layers, double learning_rate) throws IOException;
}

package lab5.net;

import lab5.helpers.Tuple;
import lab5.layers.ILayer;

import java.io.IOException;
import java.util.List;

public interface INN {
    void addLayer(ILayer layer);
    void removeLayer(int index);
    void train(double learningRate) throws IOException;
    void predict(List<Tuple> list);
}

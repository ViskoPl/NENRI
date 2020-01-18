package layers;

import java.util.Random;

public class InputLayer implements ILayer {
    private int numberOfNodes;
    private double bias;

    InputLayer(int numberOfNodes) {
        super();
        this.numberOfNodes = numberOfNodes;
        this.bias = new Random().nextInt();
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }
}

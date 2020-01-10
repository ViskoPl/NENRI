package lab5.layers;
import lab5.helpers.np;

import java.util.Random;

public class HiddenLayer implements ILayer{
    private int numberOfNodes;
    private int numberOfInputs;
    private int numberOfOutputs;
    private double[][] bias;
    private double[][] weights;

    public HiddenLayer(int numberOfNodes, int numberOfInputs, int numberOfOutputs) {
        super();
        this.numberOfNodes = numberOfNodes;
        this.numberOfOutputs = numberOfOutputs;
        this.numberOfInputs = numberOfInputs;
        this.bias = np.random(numberOfNodes,1);
        this.weights = np.random(numberOfNodes, numberOfInputs);
    }

    public double[][] output(double[][] X) {
        return np.add(np.dot(this.weights, X), this.bias);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public void setNumberOfInputs(int numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
    }

    public double[][] getBias() {
        return bias;
    }

    public void setBias(double[][] bias) {
        this.bias = bias;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public void setNumberOfOutputs(int numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
    }
}

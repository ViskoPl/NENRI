package net;

import datasets.Dataset;
import helpers.ArrayHelpers;

public class NN {
    private int[] layers;
    private double[] outputs;
    private Dataset dataset;

    public NN(int[] layers, Dataset dataset) {
        super();
        this.dataset = dataset;
        this.layers = layers;
    }


    public int[] getLayers() {
        return layers;
    }

    public void setLayers(int[] layers) {
        this.layers = layers;
    }

    public double[] getOutputs() {
        return outputs;
    }

    public void setOutputs(double[] outputs) {
        this.outputs = outputs;
    }


    public int getNumberOfParameters() {
        int number = 0;
        for(int i = 0; i < this.layers.length -1; i++) {
            number += this.layers[i] * this.layers[i+1] + this.layers[i+1];
            if(i != 0) {
                number += this.layers[i];
            }
        }

        return number;
    }

    double calculateDistance(double[] params) {
        double cal = 0;
        for(int i = 0; i < outputs.length; i++) {
            cal += Math.abs(outputs[i] - params[i])/Math.abs(1);
        }

        return 1/(1+cal);
    }

    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

    double calculateSigm(double[] params) {
        double cal = 0;
        for(int i = 0; i < params.length; i++) {
            cal += i == 0 ? 1 * params[i] : outputs[i-1]*params[i];
        }

        return sigmoid(cal);
    }

    public double[] calculateOutput(double[] params, double[] xy) {
        setOutputs(xy);
        double[] y = null;
        int previousLayerLastIndex = 0;
        for(int i = 1; i < layers.length; i++) {
            y = new double[layers[i]];
            for(int j = 0; j < layers[i]; j++) {
                double[] slice = ArrayHelpers.getSliceOfArray(params, previousLayerLastIndex + j * layers[i-1],previousLayerLastIndex + j * layers[i-1] + layers[i-1]);
                if(i == 1) {
                    y[j] = calculateDistance(slice);
                } else {
                    y[j] = calculateSigm(slice);
                }
            }

            setOutputs(y);

            previousLayerLastIndex = previousLayerLastIndex + layers[i] * layers[i-1];


        }

        return y;
    }

    public double calcError(double[] params) {
        double error = 0;
        double[] y;
        double[] slice;
        double[] currentInput;
        double[] realOutput;
        for (int i = 0; i < dataset.getLineCount(); i++) {
            currentInput = dataset.getDataset()[i];
            slice = ArrayHelpers.getSliceOfArray(currentInput, 0, 2);
            realOutput = ArrayHelpers.getSliceOfArray(currentInput, 2, 5);
            y = calculateOutput(params, slice);

            for(int j = 0; j < realOutput.length; j++) {
                error += Math.pow((realOutput[j] - y[j]),2);
            }
        }

        return error/dataset.getLineCount();
    }
}

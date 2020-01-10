package lab5.algorithms;

import lab5.helpers.np;
import lab5.layers.HiddenLayer;
import lab5.layers.ILayer;
import lab5.layers.OutPutLayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BackPropagation implements IAlgorithm {
    @Override
    public void train(List<ILayer> layers, double learning_rate) throws IOException {
        String[] list = {"00001", "00010", "00100", "01000", "10000"};
        List<double[][]> listX = new ArrayList<>();
        List<double[][]> listY = new ArrayList<>();
        for(String l: list) {
            File folder = new File("./lab5/drawings/L" + l);
            double[][] Y = new double[1][5];
            String[] s = l.split("");
            for(int i = 0; i < s.length; i++) {
                Y[0][i] = Double.parseDouble(s[i]);
            }

            for (final File fileEntry : folder.listFiles()) {
                double[][] X = new double[1][40];

                BufferedReader br = new BufferedReader(new FileReader(fileEntry));
                String line;
                int index = 0;
                while((line = br.readLine()) != null) {
                    String[] numbers = line.split(" ");
                    for(String number: numbers) {
                        String[] value = number.split(",");
                        X[0][index] = Double.parseDouble(value[0]);
                        index++;
                    }
                }

                listX.add(np.T(X));
                listY.add(np.T(Y));
            }
        }

        double[][] absolute_dw1 = new double[2][40];
        double[][] absolute_db1 = new double[2][1];
        double[][] absolute_dw2 = new double[2][2];
        double[][] absolute_db2 = new double[2][1];
        double[][] absolute_dw3 = new double[5][2];
        double[][] absolute_db3 = new double[5][1];

        double current_error = Double.POSITIVE_INFINITY;
        for(int i = 0; i < 50; i++) {
            double cost = 0;
            for(int m = 0; m < listX.size(); m++) {
                int j = 0;
               //Layer 1
               double[][] Z1 = np.add(np.dot(((HiddenLayer) layers.get(0)).getWeights(), listX.get(j)), ((HiddenLayer) layers.get(0)).getBias());
               double[][] A1 = np.sigmoid(Z1);

               //LAYER 2
               double[][] Z2 = np.add(np.dot(((HiddenLayer) layers.get(1)).getWeights(), A1), ((HiddenLayer) layers.get(1)).getBias());
               double[][] A2 = np.sigmoid(Z2);

               //Layer 3
               double[][] Z3 = np.dot(((OutPutLayer) layers.get(2)).getWeights(), A2);
               double[][] A3 = np.sigmoid(Z3);

               cost += np.cross_entropy(1, listY.get(j), A3);
//               System.out.println(cost);

               //LAYER 3
               double[][] dZ3 = np.subtract(A3, listY.get(j));
               double[][] dW3 = np.dot(dZ3, np.T(A2));
               double[][] db3 = dZ3;

               //LAYER 2
               double[][] dZ2 = np.dot(np.T(((OutPutLayer) layers.get(2)).getWeights()), dZ3);
               double[][] dW2 = np.dot(dZ2, np.T(A1));
               double[][] db2 = dZ2;

               //LAYER 1
               double[][] dZ1 = np.dot(np.T(((HiddenLayer) layers.get(1)).getWeights()), dZ2);
               double[][] dW1 = np.dot(dZ1, np.T(listX.get(j)));
               double[][] db1 = dZ1;

               //Uncomment for stohastic
               absolute_dw1 = np.add(absolute_dw1, dW1);
               absolute_db1 = np.add(absolute_db1, db1);
               absolute_dw2 = np.add(absolute_dw2, dW2);
               absolute_db2 = np.add(absolute_db2, db2);
               absolute_dw3 = np.add(absolute_dw3, dW3);
               absolute_db3 = np.add(absolute_db3, db3);

//
//               ((HiddenLayer) layers.get(0)).setWeights(np.subtract(((HiddenLayer) layers.get(0)).getWeights(), np.multiply(learning_rate, dW1)));
//               ((HiddenLayer) layers.get(0)).setBias(np.subtract(((HiddenLayer) layers.get(0)).getBias(), np.multiply(learning_rate, db1)));
//
//               ((HiddenLayer) layers.get(1)).setWeights(np.subtract(((HiddenLayer) layers.get(1)).getWeights(), np.multiply(learning_rate, dW2)));
//               ((HiddenLayer) layers.get(1)).setBias(np.subtract(((HiddenLayer) layers.get(1)).getBias(), np.multiply(learning_rate, db2)));
//
//               ((OutPutLayer) layers.get(2)).setWeights(np.subtract(((OutPutLayer) layers.get(2)).getWeights(), np.multiply(learning_rate, dW3)));
//               ((OutPutLayer) layers.get(2)).setBias(np.subtract(((OutPutLayer) layers.get(2)).getBias(), np.multiply(learning_rate, db3)));

            }
            if(current_error < cost ) {
                learning_rate /= 2;
            }
            System.out.println(cost);
            current_error = cost;
            ((HiddenLayer) layers.get(0)).setWeights(np.subtract(((HiddenLayer) layers.get(0)).getWeights(), np.multiply(learning_rate, absolute_dw1)));
            ((HiddenLayer) layers.get(0)).setBias(np.subtract(((HiddenLayer) layers.get(0)).getBias(), np.multiply(learning_rate, absolute_db1)));

            ((HiddenLayer) layers.get(1)).setWeights(np.subtract(((HiddenLayer) layers.get(1)).getWeights(), np.multiply(learning_rate, absolute_dw2)));
            ((HiddenLayer) layers.get(1)).setBias(np.subtract(((HiddenLayer) layers.get(1)).getBias(), np.multiply(learning_rate, absolute_db2)));

            ((OutPutLayer) layers.get(2)).setWeights(np.subtract(((OutPutLayer) layers.get(2)).getWeights(), np.multiply(learning_rate, absolute_dw3)));
            ((OutPutLayer) layers.get(2)).setBias(np.subtract(((OutPutLayer) layers.get(2)).getBias(), np.multiply(learning_rate, absolute_db3)));
        }
    }
}

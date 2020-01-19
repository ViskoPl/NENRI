import datasets.Dataset;
import net.NN;
import ga.GeneticAlgorithm;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main01 {

    public static void main(String[] args) throws IOException {
        int[] layers = new int[3];
        layers[0] = 2;
        layers[1] = 8;
        layers[2] = 3;
        Dataset set = new Dataset("./src/GANN/datasets/datasets.txt");
        NN net = new NN(layers, set);
        GeneticAlgorithm demo = new GeneticAlgorithm(60, 0.1, 0.05, true, net, 2, 250, 100000, 2, 1, 1);
        demo.start();

        double[] xy = new double[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the x:");
        xy[0] = sc.nextDouble();
        System.out.println("Please enter the y:");
        xy[1] = sc.nextDouble();
        System.out.println(Arrays.toString(demo.calculateOutput((xy))));

        while (true){
            System.out.println("Do you want to continue testing:");
            Double input = sc.nextDouble();
            if (input == 0){
                break;
            } else {
                System.out.println("Please enter the x:");
                xy[0] = sc.nextDouble();
                System.out.println("Please enter the y:");
                xy[1] = sc.nextDouble();
                System.out.println(Arrays.toString(demo.calculateOutput((xy))));
            }
        }

    }
}

package GeneticAlgorithm;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GeneticAlgorithm demo = new GeneticAlgorithm(100, 0.05, true, "./GeneticAlgorithm/GeneticAlgorithm/zad4-dataset1.txt", 501, "tournament", 0.5);
        demo.start();

    }
}

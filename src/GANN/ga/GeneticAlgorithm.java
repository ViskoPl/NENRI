package ga;
import net.NN;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

    Population population;
    int populationSize;
    double mutationPropability;
    boolean elitism;
    double sigma;
    NN net;
    int printEvery;
    int numberOfIterations;
    Random r = new Random();
    int[] t;

    public GeneticAlgorithm(int populationSize, double mutationProbability, boolean elitism, NN net, double sigma, int printEvery, int numberOfIterations, int... t) {
        super();
        this.populationSize = populationSize;
        this.population = new Population(populationSize, net);
        this.sigma = sigma;
        this.mutationPropability = mutationProbability;
        this.elitism= elitism;
        this.net = net;
        this.t = t;
        this.numberOfIterations = numberOfIterations;
        this.printEvery = printEvery;
    }

    public void start() {
        int iterations = 1;
        while(population.getFittest().getError() > 10E-6) {
            canonicalSelection();

            ++iterations;

            if(iterations % this.printEvery == 0) {
                System.out.println("In the iteration " + iterations + " ==> Fittest unit is: " +  population.getFittest().getError());
            }
            if(iterations > this.numberOfIterations) {
                break;
            }
        }

        System.out.println("End of optimisation ==> Fittest unit is: " +  population.getFittest().getError());

        return;
    }

    private void canonicalSelection()  {
        Unit[] newPopulation = new Unit[populationSize];
        int offset = 0;
        if(elitism) {
            newPopulation[0] = population.getFittest();
            offset = 1;
        }

        this.population.sort();
        for(int i = offset; i < populationSize; i++) {
            int[] indexes = rouletteWheelSelection(2, populationSize);
            int type = r.nextInt(3 - 1 + 1) + 1;
            if(type == 1) newPopulation[i] = population.getUnit(indexes[0]).crossover(population.getUnit(indexes[1]));
            if(type == 2) newPopulation[i] = population.getUnit(indexes[0]).crossover2(population.getUnit(indexes[1]));
            if(type == 3) newPopulation[i] = population.getUnit(indexes[0]).crossover3(population.getUnit(indexes[1]));

            int mutationType = pickMutation();
            if(mutationType == 0) newPopulation[i].mutate(mutationPropability, this.sigma);
            if(mutationType == 1) newPopulation[i].mutate(mutationPropability, this.sigma + 2);
            if(mutationType == 2) newPopulation[i].mutate2(mutationPropability, this.sigma);

        }

        this.population.setPopulation(newPopulation);
    }


    private int pickMutation() {
        int totalSum = 0;
        for (int i = 0; i < t.length; i++) {
            totalSum += t[i];
        }

        int index = r.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + t[i++];
        }

        return Math.max(0, i-1);


    }

    private int[] rouletteWheelSelection(int numberOfUnitsToTake, int border) {
        double totalFitness = this.population.getTotalFitness(border);

        int[] takenIndexes = new int[numberOfUnitsToTake];
        Arrays.fill(takenIndexes, -1);

        double runningSum = 0;
        int index = 0;
        for (int i = 0; i < numberOfUnitsToTake; i++) {
            double randomValue = (totalFitness) * r.nextDouble();
            index = 0;
            runningSum = 0;
            while (runningSum < randomValue) {
                if(index == border) {
                    index = border - 1;
                }

                runningSum += this.population.getUnit(index).getFitness();
                index++;
            }

            int finalIndex = index - 1;
            if( Arrays.stream(takenIndexes).anyMatch(j -> j == finalIndex)) {
                --i;
            } else {
                takenIndexes[i] = index - 1;
            }

        }

        return takenIndexes;
    }

    public double[] calculateOutput(double[] xy) {
        return net.calculateOutput(population.getFittest().weightArray, xy);
    }
}

package ga;

import net.NN;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

public class Unit implements Comparable<Unit> {
    double[] weightArray;
    double error = 0;
    double fitness = 0;
    private NN net;
    static Random r = new Random();

    Unit(double[] array, NN net) {
        super();
        this.weightArray = array;
        this.net = net;
        this.calculateError();
    }

    Unit(NN net) {
        super();
        this.net = net;
        this.weightArray = new double[net.getNumberOfParameters()];
        generateUnit();
        this.calculateError();
    }

    private void calculateError() {
        this.error = net.calcError(this.weightArray);

        this.fitness = 1/this.error;
    }


    void generateUnit() {
        for(int i = 0; i < this.net.getNumberOfParameters(); i++) {
            weightArray[i] = 0 + (1 - 0) * r.nextDouble();
        }

        calculateError();
    }

    double getWeight(int position) {
        return this.weightArray[position];
    }

    double getError() {
        return this.error;
    }

    double getFitness() {
        return this.fitness;
    }

    public Unit crossover3(Unit second) {

        double[] array1 = this.weightArray;
        double[] array2 = second.getWeightArray();
        double[] array = new double[this.net.getNumberOfParameters()];
        for(int i = 0; i < array1.length; i++) {
            if(i % 2 == 0) {
                array[i] = array1[i];
            } else {
                array[i] = array2[i];
            }
        }

        Unit offSpring = new Unit(array, this.net);

        return offSpring;
    }

    public Unit crossover(Unit second) {

        double[] array1 = this.weightArray;
        double[] array2 = second.getWeightArray();
        double[] array = new double[this.net.getNumberOfParameters()];
        for(int i = 0; i < array1.length; i++) {
            array[i] = r.nextDouble() < 0.5 ? array1[i] : array2[i];
        }

        Unit offSpring = new Unit(array, this.net);

        return offSpring;
    }

    public Unit crossover2(Unit second) {
        double[] array1 = this.weightArray;
        double[] array2 = second.getWeightArray();
        double[] array = new double[array1.length];
        int random = r.nextInt(2);

        for(int i = 0; i < array1.length; i++) {
            array[i] = random==0 ? array1[i] : array2[i];
        }


        Unit offSpring = new Unit(array, this.net);

        return offSpring;
    }

    public void mutate2(double probability, double sigma) {
        for(int i = 0; i < weightArray.length; i++) {
            if(r.nextDouble() < probability) {
                weightArray[i] = r.nextGaussian() * sigma;
            }
        }

        this.calculateError();
    }

    public void mutate(double probability, double sigma) {
        for(int i = 0; i < weightArray.length; i++) {
            if(r.nextDouble() < probability) {
                weightArray[i] += r.nextGaussian() * sigma;
            }
        }

        this.calculateError();
    }

    @Override
    public int compareTo(Unit o) {
        return Double.compare(o.getFitness(), this.getFitness());
    }

    public double[] getWeightArray() {
        return weightArray;
    }

}

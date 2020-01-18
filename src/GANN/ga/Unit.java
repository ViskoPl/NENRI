package ga;

import java.io.FileNotFoundException;
import java.util.Random;

public class Unit implements Comparable<Unit> {
    DoubleGene[] bArray = new DoubleGene[5];
    double error = 0;
    double fitness = 0;
    static Random r = new Random();

    Unit(DoubleGene ...array) throws FileNotFoundException {
        super();
        this.bArray = array;
        this.calculateError();
    }

    Unit() throws FileNotFoundException {
        super();

        generateUnit();
        this.calculateError();
    }


    void generateUnit() {

        for(int i = 0; i < 5; i++) {
            bArray[i] = new DoubleGene(-4 + (4 - -4) * r.nextDouble());
        }
    }

    DoubleGene getGene(int position) {
        return this.bArray[position];
    }

    double getError() {
        return this.error;
    }

    double getFitness() {
        return this.fitness;
    }

    double func(double x, double y) {
        return Math.sin(bArray[0].getValue() + bArray[1].getValue()*x) + bArray[2].getValue() * Math.cos(x * (bArray[3].getValue() + y))*(1/(1 + Math.exp(Math.pow(x - bArray[4].getValue(), 2))));
    }

    public void calculateError() throws FileNotFoundException {
        double sum = 0;
        for(int i = 0; i < 250; i++) {
            sum += Math.pow(func(GeneticAlgorithm.currentMatrix[i][0], GeneticAlgorithm.currentMatrix[i][1]) - GeneticAlgorithm.currentMatrix[i][2], 2);
        }

        this.error = sum / 250;
        this.fitness = 1 / this.error;
    }

    public static Unit crossover(Unit first, Unit second, double crossoverProbability) throws FileNotFoundException {
        DoubleGene[] array = new DoubleGene[5];
        for(int i = 0; i < 5; i++) {
            array[i] = DoubleGene.crossover(first.getGene(i), second.getGene(i), crossoverProbability);
        }

        Unit offSpring = new Unit(array);

        return offSpring;
    }

    public void mutate(double probability) throws FileNotFoundException {
        for(int i = 0; i < 5; i++) {
            bArray[i].mutate(probability);
        }

        this.calculateError();
    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < bArray.length; i++) {
            out += "B" + i + ": " + bArray[i].getValue() + " ";
        }

        return out;
    }

    @Override
    public int compareTo(Unit o) {
        return Double.compare(o.getFitness(), this.getFitness());
    }
}

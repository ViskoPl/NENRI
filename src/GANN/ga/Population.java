package ga;

import net.NN;

import java.io.FileNotFoundException;
import java.util.Arrays;

class Population {

    private int popSize;
    private Unit[] population;

    Population(int size, NN net)  {
        super();

        this.popSize = size;
        this.population = new Unit[size];
        this.initializePopulation(net);
    }

    public Unit getUnit(int location) {
        return this.population[location];
    }

    void initializePopulation(NN net) {
        for (int i = 0; i < popSize; i++) {
            this.population[i] = new Unit(net);
        }
    }

    Unit getFittest() {
        double maxFitness = 0;
        int index = 0;
        double currentFitness = 0;
        for(int i = 0; i < popSize; i++) {
            currentFitness = this.population[i].getFitness();
            if(maxFitness < currentFitness) {
                maxFitness = currentFitness;
                index = i;
            }
        }

        return this.population[index];
    }

    public double getTotalFitness(int n) {
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum += population[i].getFitness();
        }

        return sum;
    }

    public void sort() {
        Arrays.sort(this.population);
    }

    public void setPopulation(Unit[] newPopulation) {
        this.population = newPopulation;
    }

}


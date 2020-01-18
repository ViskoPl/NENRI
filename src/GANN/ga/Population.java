package ga;

import java.io.FileNotFoundException;
import java.util.Arrays;

class Population {

    int popSize = 10;
    Unit[] population;

    Population(int size) throws FileNotFoundException {
        super();

        this.popSize = size;
        this.population = new Unit[size];
        this.initializePopulation();
    }

    public void setUnit(int location, Unit unit) {
        this.population[location] = null;
        this.population[location] = unit;
    }

    public Unit getUnit(int location) {
        return this.population[location];
    }

    void initializePopulation() throws FileNotFoundException {
        for (int i = 0; i < popSize; i++) {
            this.population[i] = new Unit();
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

    public int getIndexOfWorst() {
        int index = 0;
        double worst = this.population[0].getFitness();
        double currentFitness = 0;
        for(int i = 1; i < this.population.length; i++) {
            currentFitness = this.population[i].getFitness();
            if(currentFitness < worst) {
                worst = currentFitness;
                index = i;
            }
        }

        return index;
    }

    public int getIndexOfFittest() {
        int index = 0;
        double fittest = this.population[0].getFitness();
        double currentFitness = 0;
        for(int i = 1; i < this.population.length; i++) {
            currentFitness = this.population[i].getFitness();
            if(currentFitness > fittest) {
                fittest = currentFitness;
                index = i;
            }
        }

        return index;
    }

    public double getTotalFitness(int n) {
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum += population[i].getFitness();
        }

        return sum;
    }

    public Unit getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < this.population.length; i++) {
            if (this.population[i].getFitness() > this.population[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (this.population[i].getFitness() > this.population[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        return this.population[maxFit2];
    }

    public void sort() {
        Arrays.sort(this.population);
    }

    public void setPopulation(Unit[] newPopulation) {
        this.population = newPopulation;
    }

    public void mutate(double probability) throws FileNotFoundException {
        for(int i = 0; i < this.popSize; i++) {
            this.population[i].mutate(probability);
        }
    }

}


package ga;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm {

    Population population;
    int populationSize = 0;
    double mutationPropability;
    boolean elitism = false;
    int numberOfIterations = 0;
    double crossOverProbability;
    static double[][] currentMatrix;
    int generationNumber = 1;
    String selectionType;

    GeneticAlgorithm(int populationSize, double mutationProbability, boolean elitism, String dataset, int numberOfIterations, String selectionType, double crossOverProbility) throws FileNotFoundException {
        super();
        fillDataSets(dataset);
        this.populationSize = populationSize;
        this.population = new Population(populationSize);
        this.mutationPropability = mutationProbability;
        this.elitism= elitism;
        this.numberOfIterations = numberOfIterations;
        this.selectionType = selectionType;
        this.crossOverProbability = crossOverProbility;
    }

    void fillDataSets(String file) throws FileNotFoundException {
        File file1 = new File(file);
        GeneticAlgorithm.currentMatrix = new double[250][3];
        int i = 0;
        Scanner scanner = new Scanner(file1);
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split("\t");
            GeneticAlgorithm.currentMatrix[i][0] = Double.parseDouble(values[0]);
            GeneticAlgorithm.currentMatrix[i][1] = Double.parseDouble(values[1]);
            GeneticAlgorithm.currentMatrix[i][2] = Double.parseDouble(values[2]);
            ++i;
        }
    }

    void start() throws FileNotFoundException {
        int iterations = 1;
        while(population.getFittest().getError() > 10E-6) {
            if(selectionType == "tournament") {
                this.population.sort();
                tournamentSelection();
                if(iterations % 1000 == 0) {
                    System.out.println("In the iteration " + iterations + " ==> Fittest unit is: " +  population.getFittest() + " " + population.getFittest().getError());
                }
            } else {
                canonicalSelection();
                if(iterations % 100 == 0) {
                    System.out.println("In the iteration " + iterations + " ==> Fittest unit is: " +  population.getFittest() + " " +  population.getFittest().getError());
                }
            }

            ++iterations;
            ++this.generationNumber;
        }

        System.out.println("In the iteration " + iterations + " ==> Fittest unit is: " +  population.getFittest());





        return;
    }

    private void canonicalSelection() throws FileNotFoundException {
        Unit[] newPopulation = new Unit[populationSize];
        int offset = 0;
        if(elitism) {
            newPopulation[0] = population.getFittest();
            offset = 1;
        }

        int selectNumberOfChildren = (int) (0.5 * (this.populationSize));
        this.population.sort();
        for(int i = offset; i < populationSize; i++) {
            int[] indexes = rouletteWheelSelection(2, selectNumberOfChildren);
            newPopulation[i] = Unit.crossover(population.getUnit(indexes[0]), population.getUnit(indexes[1]), crossOverProbability);
            newPopulation[i].mutate(mutationPropability);
        }

        this.population.setPopulation(newPopulation);
    }


    private void tournamentSelection() throws FileNotFoundException {
        Population tournament = new Population(3);
        int[] indexes = rouletteWheelSelection(3, 100);

        for (int i = 0; i < 3; i++) {
            tournament.setUnit(i, population.getUnit(indexes[i]));
        }

        int indexOfWorst = tournament.getIndexOfWorst();
        Unit newUnit = Unit.crossover(tournament.getFittest(), tournament.getSecondFittest(), crossOverProbability);
        newUnit.mutate(mutationPropability);
        this.population.setUnit(indexes[indexOfWorst], newUnit);
    }

    private int[] rouletteWheelSelection(int numberOfUnitsToTake, int border) {
        Random random = new Random();
        double totalFitness = this.population.getTotalFitness(border);

        int[] takenIndexes = new int[numberOfUnitsToTake];
        Arrays.fill(takenIndexes, -1);

        double runningSum = 0;
        int index = 0;
        for (int i = 0; i < numberOfUnitsToTake; i++) {
            double randomValue = (totalFitness) * random.nextDouble();
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

//        System.out.println(Arrays.toString(takenIndexes));

        return takenIndexes;
    }
}

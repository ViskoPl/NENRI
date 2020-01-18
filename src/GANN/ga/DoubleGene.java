package GeneticAlgorithm;

import java.math.BigInteger;
import java.util.Random;

class DoubleGene {
    int sign = 0;
    double left;
    double right;
    static Random rn = new Random();

    DoubleGene(double x) {
        super();
        this.fillBits(x);
    }

    DoubleGene() {
        super();
    }

    void fillBits(double x) {
        double y = Math.abs(x);
        double wholePart = (int) y;
        double decimalPart = y - ((int) y);

        this.left = wholePart;
        this.right = decimalPart;

        if( x < 0) {
            this.sign = 1;
        }

    }

    public double getValue() {
        double value = this.left + this.right;

        return this.sign == 0 ? value : -value;
    }


    public static DoubleGene crossover(DoubleGene first, DoubleGene second, double crossOverProbability) {
        return new DoubleGene((first.getValue() + second.getValue())/ 2);
    }

    public void mutate(double probability) {
        if(rn.nextDouble() < probability) {
            double y = -4 + (4 - -4) * rn.nextDouble();
            this.fillBits(y);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

}

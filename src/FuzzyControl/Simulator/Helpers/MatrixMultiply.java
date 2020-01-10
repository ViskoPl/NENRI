package Simulator.Helpers;

import zad1.Domain;
import zad1.DomainElement;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;

public class MatrixMultiply {
    public static IFuzzySet MatrixMultiply(IFuzzySet set, double multiplier) {
        IFuzzySet newSet = new MutableFuzzySet(set.getDomain());

        for(int i = 0; i < set.getDomain().getCardinality(); i++) {
            ((MutableFuzzySet) newSet).set(DomainElement.of(i), set.getValueAt(DomainElement.of(i)) * multiplier);
        }

        return newSet;
    }
}

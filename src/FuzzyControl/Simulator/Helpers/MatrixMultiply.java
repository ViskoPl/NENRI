package Simulator.Helpers;

import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad2.MutableFuzzySet;

public class MatrixMultiply {
    public static IFuzzySet MatrixMultiply(IFuzzySet set, double multiplier) {
        IFuzzySet newSet = new MutableFuzzySet(set.getDomain());

        for(int i = set.getDomain().getFirst(); i < set.getDomain().getLast(); i++) {
            ((MutableFuzzySet) newSet).set(DomainElement.of(i), set.getValueAt(DomainElement.of(i)) * multiplier);
        }

        return newSet;
    }
}

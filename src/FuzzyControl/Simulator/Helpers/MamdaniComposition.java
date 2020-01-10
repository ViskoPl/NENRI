package Simulator.Helpers;

import zad1.Domain;
import zad1.DomainElement;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;
import zad3.Operations;

public class MamdaniComposition {
    public static IFuzzySet MamdaniComposition(IFuzzySet set1, IFuzzySet set2, String type) {
        IFuzzySet newSet = new MutableFuzzySet(Domain.combine(Domain.intRange(0, 7), Domain.intRange(0,7)));

        for(int i = 0; i < set1.getDomain().getCardinality(); i++) {
            for( int j = 0; j < set2.getDomain().getCardinality(); j++) {
                if( type == "m") {
                    ((MutableFuzzySet) newSet).set(DomainElement.of(i, j), Operations.zadehAnd().valueAt(set1.getValueAt(DomainElement.of(j)), set2.getValueAt(DomainElement.of(j))));
                } else {
                    ((MutableFuzzySet) newSet).set(DomainElement.of(i, j), set1.getValueAt(DomainElement.of(j)) * set2.getValueAt(DomainElement.of(j)));
                }
            }
        }

        return newSet;
    }
}

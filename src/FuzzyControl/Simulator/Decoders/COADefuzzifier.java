package Simulator.Decoders;

import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad2.MutableFuzzySet;

public class COADefuzzifier implements IDecoder {

    public COADefuzzifier() {
        super();
    }

    @Override
    public double decode(IFuzzySet set) {
        double upperSum = 0;
        double lowerSum = 0;
        MutableFuzzySet set1 = ((MutableFuzzySet) set);
        for(DomainElement x: set1.getDomain()) {
            upperSum += set.getValueAt(x) * x.getValues()[0];
            lowerSum += set.getValueAt(x);
        }

        if (upperSum == 0.0) {
            return 0.0;
        } else {
            return upperSum/lowerSum;
        }
    }
}

package Simulator.Decoders;

import zad1.DomainElement;
import zad1.SimpleDomain;
import zad2.IFuzzySet;

public class COADefuzzifier implements IDecoder {
    int[] values;

    public COADefuzzifier() {
        super();
    }

    @Override
    public double decode(IFuzzySet set) {
        double upperSum = 0;
        double lowerSum = 0;
        for(DomainElement x: set.getDomain()) {
            upperSum += set.getValueAt(x) * x.getComponentValue(0);
            lowerSum += set.getValueAt(x);
        }

        if (upperSum == 0.0) {
            return 0.0;
        } else {
            return upperSum/lowerSum;
        }
    }
}

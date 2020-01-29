package Simulator.Systems;

import Simulator.Databases.IDatabase;
import Simulator.Decoders.IDecoder;
import FuzzyOperations.zad2.IFuzzySet;

public class AccelerationFuzzySystem extends FuzzySystem{

    public AccelerationFuzzySystem(IDecoder dec, IDatabase dat) {
        super(dec, dat);
    }

    @Override
    public double conclude(int L, int D, int LK, int DK, int V, int S) {
        IFuzzySet y = this.dat.calculate(L, D, LK, DK, V, S);

        return (int) Math.round(this.dec.decode(y));
    }
}

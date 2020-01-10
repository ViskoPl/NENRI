package Simulator.Systems;

import Simulator.Databases.IDatabase;
import Simulator.Decoders.IDecoder;
import Simulator.Rules.CombinedRule;
import Simulator.Rules.Rule;
import zad2.IFuzzySet;
import zad4.Relations;

public class HelmFuzzySystem extends FuzzySystem {

    public HelmFuzzySystem(IDecoder dec, IDatabase dat) {
        super(dec, dat);
    }


    @Override
    public double conclude(int L, int D, int DK, int LK, int V, int S) {
        IFuzzySet y = this.dat.calculate(L, D, DK, LK, V, S);

        return this.dec.decode(y);
    }
}

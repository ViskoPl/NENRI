package Simulator.Systems;

import Simulator.Databases.IDatabase;
import Simulator.Decoders.IDecoder;

public abstract class FuzzySystem implements IFuzzySystem {
    IDecoder dec;
    IDatabase dat;

    FuzzySystem(IDecoder dec, IDatabase dat) {
        super();

        this.dec = dec;
        this.dat = dat;
    }

    public IDecoder getDecoder() {
        return this.dec;
    }

    public void setDecoder(IDecoder dec) {
        this.dec = dec;
    }

    public IDatabase getDatabase() {
        return this.dat;
    }

    public void setDatabase(IDatabase dat) {
        this.dat = dat;
    }
}

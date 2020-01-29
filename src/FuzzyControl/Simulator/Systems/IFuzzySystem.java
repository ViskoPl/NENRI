package Simulator.Systems;

import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad3.Operations;
import FuzzyRelations.zad4.Relations;

public interface IFuzzySystem {
    double conclude(int L, int D, int DK, int LK, int V, int S);
}

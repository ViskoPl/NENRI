package Simulator.Databases;

import FuzzyOperations.zad2.IFuzzySet;

public interface IDatabase {
    IFuzzySet calculate(int L, int D, int LK, int DK, int V, int S);
}

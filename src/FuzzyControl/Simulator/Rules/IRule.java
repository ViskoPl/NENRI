package Simulator.Rules;

import Simulator.Systems.IFuzzySystem;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad2.MutableFuzzySet;
import FuzzyOperations.zad3.Operations;

public interface IRule {
    public double calculate(int L, int D, int LK, int DK, int V, int S);
}

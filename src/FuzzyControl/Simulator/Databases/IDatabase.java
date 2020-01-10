package Simulator.Databases;

import Simulator.Rules.IRule;
import zad2.IFuzzySet;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDatabase {
    IFuzzySet calculate(int L, int D, int LK, int DK, int V, int S);
}

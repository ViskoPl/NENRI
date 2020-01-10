package Simulator.Rules;

import Simulator.Systems.IFuzzySystem;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;
import zad3.Operations;

public class CombinedRule implements IRule {
    IRule[] rules;

    public CombinedRule(IRule  ...rules) {
        super();
        this.rules = rules;
    }

    @Override
    public double calculate(int L, int D, int LK, int DK, int V, int S) {
        double result = Operations.zadehAnd().valueAt(
                rules[0].calculate(L, D, LK, DK, V, S),
                rules[1].calculate(L, D, LK, DK, V, S)
        );
        for (int i = 2; i < rules.length; ++i) {
            result = Operations.zadehAnd().valueAt(result, rules[i].calculate(L, D, LK, DK, V, S));
        }
        return result;
    }
}

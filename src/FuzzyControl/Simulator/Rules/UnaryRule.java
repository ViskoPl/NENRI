package Simulator.Rules;

import FuzzyOperations.zad3.IUnaryFunction;
import FuzzyOperations.zad3.Operations;

public class UnaryRule implements IRule {
        IRule rule1;
        IUnaryFunction fun;


    public UnaryRule(IRule rule1, IUnaryFunction fun) {
        super();
        this.rule1 = rule1;
        this.fun = fun;
    }

    @Override
    public double calculate(int L, int D, int LK, int DK, int V, int S) {
        return fun.valueAt(rule1.calculate(L, D, LK, DK, V, S));
    }
}

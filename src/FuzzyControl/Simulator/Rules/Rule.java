package Simulator.Rules;

import zad1.DomainElement;
import zad2.IFuzzySet;

public class Rule implements IRule {
    IFuzzySet set;
    String type;

    public Rule(IFuzzySet set, String type) {
        super();

        this.type = type;
        this.set = set;
    }

    public IFuzzySet getSet() {
        return this.set;
    }

    public String getType() {
        return this.type;
    }

    public int getValue(int L, int LK, int D, int DK, int V, int S) {
        switch (this.type) {
            case "L":
                return L;
            case "LK":
                return LK;
            case "DK":
                return DK;
            case "V":
                return V;
            case "D":
                return D;
            case "S":
                return S;
            default:
                return 0;
        }
    }

    public void setSet(IFuzzySet set) {
        this.set = set;
    }

    @Override
    public double calculate(int L, int D, int LK, int DK, int V, int S) {
        return this.set.getValueAt((DomainElement.of(getValue(L, D, LK,  DK, V, S))));
    }
}

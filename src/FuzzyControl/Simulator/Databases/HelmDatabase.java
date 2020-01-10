package Simulator.Databases;

import Simulator.Helpers.Sets;
import Simulator.Rules.*;
import zad2.IFuzzySet;
import zad2.IIntUnaryFunction;
import zad2.StandardFuzzySets;
import zad3.Operations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HelmDatabase implements IDatabase {
    private ArrayList<ImplicitExpression> rules;

    public HelmDatabase() throws IOException {
        super();
        this.rules = new ArrayList<>();

        fillRules();
    }

    public void fillRules(){

        rules.add(new ImplicitExpression(new CombinedRule( new Rule(Sets.sets.get("D").get(0), "L"),
                new Rule(Sets.sets.get("D").get(0), "LK")), Sets.sets.get("K").get(0)));

        rules.add(new ImplicitExpression(new CombinedRule(
                new Rule(Sets.sets.get("D").get(0), "L"),
                new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "LK")
                ), Sets.sets.get("K").get(2) ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Sets.sets.get("D").get(2), "L"),
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(1), Sets.sets.get("D").get(2), Operations.zadehOr()), "LK")
                ),
                Sets.sets.get("K").get(2)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Sets.sets.get("D").get(0), "D"),
                        new Rule(Sets.sets.get("D").get(0), "DK")
                ),
                Sets.sets.get("K").get(6)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Sets.sets.get("D").get(1), "D"),
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "DK")
                ),
                Sets.sets.get("K").get(5)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Sets.sets.get("D").get(1), "D"),
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(1), Sets.sets.get("D").get(2), Operations.zadehOr()), "DK")
                ),
                Sets.sets.get("K").get(4)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(0), "L"),
                Sets.sets.get("K").get(1)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(1), "L"),
                Sets.sets.get("K").get(1)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(1), "LK"),
                Sets.sets.get("K").get(1)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(2), "LK"),
                Sets.sets.get("K").get(2)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(0), "D"),
                Sets.sets.get("K").get(5)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(1), "D"),
                Sets.sets.get("K").get(5)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(2), "DK"),
                Sets.sets.get("K").get(4)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("D").get(1), "DK"),
                Sets.sets.get("K").get(5)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "L"),
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "LK"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), Operations.zadehNot()), "D"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), Operations.zadehNot()), "DK")
                ), Sets.sets.get("K").get(0)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "D"),
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), "DK"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), Operations.zadehNot()), "L"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(0), Sets.sets.get("D").get(1), Operations.zadehOr()), Operations.zadehNot()), "LK")
                ), Sets.sets.get("K").get(6)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Sets.sets.get("D").get(2), "LK"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "L"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "D"),
                        new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "DK")
                ), Sets.sets.get("K").get(1)
        ));

        rules.add(new ImplicitExpression(new CombinedRule(
                    new Rule(Sets.sets.get("D").get(2), "DK"),
                    new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "L"),
                    new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "LK"),
                    new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), Operations.zadehNot()), "D")
                ), Sets.sets.get("K").get(5)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), "LK"),
                        new UnaryRule(
                                new Rule(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), "DK"),
                                Operations.zadehNot()
                        )
                ),
                Sets.sets.get("K").get(4)
        ));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), "DK"),
                        new UnaryRule(
                                new Rule(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6), Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), "LK"),
                                Operations.zadehNot()
                        )
                ),
                Sets.sets.get("K").get(2)
        ));
    }

    @Override
    public IFuzzySet calculate(int L, int D, int LK, int DK, int V, int S) {
            IFuzzySet finalRule = rules.get(0).evaluate(L, LK, D, DK, V, S);
            for (int i = 1; i < rules.size(); ++i) {
                finalRule = Operations.binaryOperation(finalRule, rules.get(i).evaluate(L, LK, D, DK, V, S), Operations.zadehOr());
            }
            return finalRule;
    }
}

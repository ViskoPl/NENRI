package Simulator.Databases;

import Simulator.Helpers.Sets;
import Simulator.Rules.CombinedRule;
import Simulator.Rules.ImplicitExpression;
import Simulator.Rules.Rule;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad3.Operations;

import java.io.IOException;
import java.util.ArrayList;

public class AccelerationDatabase implements IDatabase {
    private ArrayList<ImplicitExpression> rules;

    public AccelerationDatabase() throws IOException {
        super();

        this.rules = new ArrayList<>();
        fillRules();
    }

    public void fillRules() {
        rules = new ArrayList<>();


//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("V").get(0), "V"),
//                Sets.sets.get("A").get(0)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("V").get(1), "V"),
//                Sets.sets.get("A").get(0)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("V").get(2), "V"),
//                Sets.sets.get("A").get(0)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("V").get(3), "V"),
//                Sets.sets.get("A").get(0)
//        ));
//
//
//        rules.add(new ImplicitExpression(
//            new Rule(Sets.sets.get("V").get(4), "V"),
//            Sets.sets.get("A").get(0)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(2), "D"),
//                Sets.sets.get("A").get(2)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(2), "L"),
//                Sets.sets.get("A").get(2)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(2), "LK"),
//                Sets.sets.get("A").get(2)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(2), "DK"),
//                Sets.sets.get("A").get(2)
//        ));
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(1), "D"),
//                Sets.sets.get("A").get(0)
//        ));
//
//
//        rules.add(new ImplicitExpression(
//                new Rule(Sets.sets.get("D").get(1), "L"),
//                Sets.sets.get("A").get(0)
//        ));
//
//
        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("V").get(1), "V"),
                Sets.sets.get("A").get(3)
        ));


        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("V").get(0), "V"),
                Sets.sets.get("A").get(6)
        ));

        rules.add(new ImplicitExpression(
                new Rule(Sets.sets.get("V").get(1), "V"),
                Sets.sets.get("A").get(4)
        ));

        IFuzzySet nVC = Operations.unaryOperation(Sets.sets.get("D").get(0), Operations.zadehNot());
        rules.add(new ImplicitExpression( new CombinedRule(
                new Rule(Sets.sets.get("D").get(0), "L"),
                new Rule(nVC, "LK"),
                new Rule(nVC, "D"),
                new Rule(nVC, "DK")
        ), Sets.sets.get("A").get(4)));

        rules.add(new ImplicitExpression(new CombinedRule(
                new Rule(Sets.sets.get("D").get(0), "D"),
                new Rule(nVC, "LK"),
                new Rule(nVC, "L"),
                new Rule(nVC, "DK")
        ), Sets.sets.get("A").get(4) ));
        IFuzzySet s = Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Operations.zadehNot());
        rules.add(new ImplicitExpression(new CombinedRule(
                new Rule(Operations.binaryOperation(Sets.sets.get("V").get(3), Sets.sets.get("V").get(4), Operations.zadehOr()), "V"),
                new Rule(Operations.binaryOperation())
                new Rule(Operations.unaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Operations.zadehNot()), "DK")
        ), Sets.sets.get("A").get(2)));

        rules.add(new ImplicitExpression(
                new CombinedRule(
                        new Rule(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), "L"),
                        new Rule(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), "LK"),
                        new Rule(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), "D"),
                        new Rule(Operations.binaryOperation(Operations.binaryOperation(Operations.binaryOperation(Sets.sets.get("D").get(6),  Sets.sets.get("D").get(5), Operations.zadehOr()), Sets.sets.get("D").get(4), Operations.zadehOr()), Sets.sets.get("D").get(3), Operations.zadehOr()), "DK")
                ), Sets.sets.get("A").get(4)
        ));
    }

    @Override
    public IFuzzySet calculate(int L, int D, int LK, int DK, int V, int S) {
        IFuzzySet finalRule = rules.get(0).evaluate(L, D, LK, DK, V, S);
        for (int i = 1; i < rules.size(); ++i) {
            finalRule = Operations.binaryOperation(finalRule, rules.get(i).evaluate(L, D, LK, DK, V, S), Operations.zadehOr());
        }
        return finalRule;
    }

}

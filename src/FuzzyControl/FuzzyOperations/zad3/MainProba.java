package FuzzyOperations.zad3;

import FuzzyOperations.zad1.Domain;
import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad1.IDomain;
import FuzzyOperations.zad2.IFuzzySet;
import FuzzyOperations.zad2.MutableFuzzySet;

public class MainProba {
  public static void main(String[] args) {
    IDomain d = Domain.intRange(0, 11);
    IFuzzySet set1 = new MutableFuzzySet(d).set(DomainElement.of(0), 1.0).set(DomainElement.of(1), 0.8)
        .set(DomainElement.of(2), 0.6).set(DomainElement.of(3), 0.4).set(DomainElement.of(4), 0.2);
    IFuzzySet notSet1 = Operations.unaryOperation(set1, Operations.zadehNot());
    IFuzzySet union = Operations.binaryOperation(set1, notSet1, Operations.zadehOr());
    IFuzzySet hinters = Operations.binaryOperation(set1, notSet1, Operations.hamacherTNorm(1.0));
  }
}
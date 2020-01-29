package FuzzyOperations.zad2;

import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad1.IDomain;

public class CalculatedFuzzySet implements IFuzzySet {
  IDomain domain;
  IIntUnaryFunction function;

  public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
    this.domain = domain;
    this.function = function;
  }

  public IDomain getDomain() {
    return this.domain;
  }

  public double getValueAt(DomainElement element) {
    return this.function.valueAt(element.getComponentValue(0));
  }

  @Override
  public double[] getMemberships() {
    return new double[0];
  }
}
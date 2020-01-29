package FuzzyOperations.zad2;

import FuzzyOperations.zad1.DomainElement;
import FuzzyOperations.zad1.IDomain;

public interface IFuzzySet {
  public IDomain getDomain();

  public double getValueAt(DomainElement element);

  double[] getMemberships();
}
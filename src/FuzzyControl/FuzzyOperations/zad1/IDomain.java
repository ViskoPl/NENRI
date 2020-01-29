package FuzzyOperations.zad1;

import FuzzyOperations.zad1.DomainElement;

public interface IDomain extends Iterable<DomainElement> {

  public int getCardinality();

  public IDomain getComponent(int position);

  public int getNumberOfComponents();

  public int indexOfElement(DomainElement elem);

  public DomainElement elementForIndex(int position);

  public int getFirst();
  public int getLast();
}
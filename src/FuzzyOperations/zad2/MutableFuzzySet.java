package zad2;

import zad1.DomainElement;
import zad1.IDomain;

public class MutableFuzzySet implements IFuzzySet {
  IDomain domain;
  double[] memberships;

  public MutableFuzzySet(IDomain domain) {
    this.memberships = new double[domain.getCardinality()];
    this.domain = domain;
  }

  public IDomain getDomain() {
    return this.domain;
  }

  public void setMemberships(double[] memberships) {
    this.memberships = memberships;
  }

  public double[] getMemberships() {
    return this.memberships;
  }

  public double getValueAt(DomainElement element) {
    return memberships[this.domain.indexOfElement(element)];
  }

  public MutableFuzzySet set(DomainElement element, double value) {
    memberships[this.domain.indexOfElement(element)] = value;
    return this;
  }
}
package FuzzyOperations.zad1;

public abstract class Domain implements IDomain {
  private int first;
  private int last;
  Domain() {
    super();
  }

  public static IDomain intRange(int first, int last) {
    return new SimpleDomain(first, last);
  }

  public static IDomain combine(IDomain first, IDomain second) {
    IDomain[] list = new IDomain[2];
    return new CompositeDomain(first, second);
  }

  public int indexOfElement(DomainElement elem) {
    int index = 0;
    for (DomainElement el : this) {
      if (el.equals(elem)) {
        return index;
      }

      ++index;
    }

    return 0;
  }

  public DomainElement elementForIndex(int position) {
    int index = 0;
    for (DomainElement el : this) {
      if (index == position) {
        return el;
      }

      index++;
    }

    return null;
  }
}
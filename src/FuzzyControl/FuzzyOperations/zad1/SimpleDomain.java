package FuzzyOperations.zad1;


import java.util.Iterator;

public class SimpleDomain extends Domain {
  private int first;
  private int last;

  SimpleDomain(int first, int last) {
    super();
    this.first = first;
    this.last = last;
  }

  public IDomain getComponent(int position) {
    return this;
  };

  public int getNumberOfComponents() {
    return 1;
  };

  public int getCardinality() {
    return this.last - this.first;
  };

  public int getFirst() {
    return this.first;
  }

  public int getLast() {
    return this.last;
  }

  @Override
  public Iterator<DomainElement> iterator() {
    Iterator<DomainElement> it = new Iterator<DomainElement> () {
      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < getCardinality();
      }

      @Override
      public DomainElement next() {
        return new DomainElement(getFirst() + currentIndex++);
      }
    };
    return it;
  }

}
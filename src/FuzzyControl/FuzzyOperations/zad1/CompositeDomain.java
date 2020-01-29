package FuzzyOperations.zad1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeDomain extends Domain {

  private IDomain[] listOfDomains;

  public CompositeDomain(IDomain... list) {
    super();
    this.listOfDomains = list;
  }

  public IDomain getComponent(int position) {
    try {
      return this.listOfDomains[position];
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }

  };

  public int getNumberOfComponents() {
    return this.listOfDomains.length;
  }

  @Override
  public int getFirst() {
    return this.getFirst();
  }

  @Override
  public int getLast() {
    return this.getLast();
  }

  ;

  public int getCardinality() {
    try {
      int cardinality = this.listOfDomains[0].getCardinality();

      for (int i = 1; i < this.listOfDomains.length; i++) {
        cardinality *= this.listOfDomains[i].getCardinality();
      }

      return cardinality;
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  };

  public IDomain[] getList() {
    return this.listOfDomains;
  }

  public Iterator<DomainElement> iterator() {
      Iterator<DomainElement> it = new Iterator<DomainElement>() {

      private int currentIndex = 0;
      private int[] weights = weights();

      @Override
      public boolean hasNext() {
        return currentIndex < CompositeDomain.this.getCardinality();
      }

      @Override
      public DomainElement next() {
        List<Integer> tmpList = new ArrayList<Integer>();
        int i = 0;

        for(IDomain domain : getList()) {
          tmpList.add(((SimpleDomain) domain).getFirst() + (currentIndex / weights[i] % ((SimpleDomain) domain).getCardinality()));
          i++;
        }

        int[] array = tmpList.stream().mapToInt(j->j).toArray();
        currentIndex++;
        return new DomainElement(array);
      }
    };
    return it;
  }

  private int[] weights() {
    int weights[] = new int[this.listOfDomains.length];
    int weight = 1;

    for(int i = this.listOfDomains.length; i > 0 ; i--) {
      weights[i-1] = weight;
      weight *= this.listOfDomains[i - 1].getCardinality();

    }


    return weights;
  }

}
package zad1;

public class Domains {
  public static void main(String[] args) {
    IDomain d1 = Domain.intRange(0, 5);
    IDomain d2 = Domain.intRange(0, 3);
    IDomain d3 = Domain.combine(d1, d2);
    System.out.println(d3.elementForIndex(0));
    System.out.println(d3.elementForIndex(5));
    System.out.println(d3.elementForIndex(14));
    System.out.println(d3.indexOfElement(DomainElement.of(4, 1)));
  }
}

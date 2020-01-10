package zad1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DomainElement {
  int[] values;

  public DomainElement(int... values) {
    super();
    this.values = values;
  }

  public int getNumberOfComponents() {
    return this.values.length;
  }

  public int getComponentValue(int position) {
    return this.values[position];
  }

  @Override
  public int hashCode() {
    return IntStream.of(this.values).sum();
  }

  public int[] getValues() {
    return this.values;
  }

  @Override
  public boolean equals(Object elem) {
    if (!(elem instanceof DomainElement)) {
      return false;
    } else {
      return Arrays.equals(this.values, ((DomainElement) elem).getValues());
    }
  }

  @Override
  public String toString() {
    if (this.values.length == 1) {
      return Integer.toString(this.values[0]);
    } else {
      return "(" + Arrays.stream(this.values).mapToObj(String::valueOf).collect(Collectors.joining(",")) + ')';
    }
  }

  public static DomainElement of(int... values) {
    return new DomainElement(values);
  }

}
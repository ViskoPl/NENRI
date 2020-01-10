package zad3;

import zad1.DomainElement;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;

public class Operations {
  Operations() {
    super();
  }

  public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction function) {
    MutableFuzzySet product = new MutableFuzzySet(set.getDomain());
    for(DomainElement elem: set.getDomain()) {
      product.set(elem, function.valueAt(set.getValueAt(elem)));
    }

    return product;
  }

  public static IFuzzySet binaryOperation(IFuzzySet first, IFuzzySet second, IBinaryFunction function ) {
    MutableFuzzySet product = new MutableFuzzySet(first.getDomain());
    for(DomainElement elem: first.getDomain()) {
      product.set(elem, function.valueAt(first.getValueAt(elem), second.getValueAt(elem)));
    }

    return product;
  }

  public static IUnaryFunction zadehNot() {
    return new IUnaryFunction() {
      @Override
      public double valueAt(double x) {
        return 1 - x;
      }
    };
  }

  public static IBinaryFunction zadehAnd() {
    return new IBinaryFunction() {
      @Override
      public double valueAt(double x, double y) {
        return x > y ? y : x;
      }
    };
  }

  public static IBinaryFunction zadehOr() {
    return new IBinaryFunction() {
      @Override
      public double valueAt(double x, double y) {
        return x > y ? x : y;
      }
    };
  }

  public static IBinaryFunction hamacherTNorm(double v) {
    return new IBinaryFunction() {
      @Override
      public double valueAt(double a, double b) {
        return (a * b) / (v + (1 - v) * (a + b - a * b));
      }
    };
  }

  public static IBinaryFunction hamacherNNorm(double v) {
    return new IBinaryFunction() {
      @Override
      public double valueAt(double a, double b) {
        return (a + b - (2 - v) * a * b) / (1 - (1 - v) * a * b);
      }
    };
  }

}
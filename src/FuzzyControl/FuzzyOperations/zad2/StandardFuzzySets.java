package FuzzyOperations.zad2;

public class StandardFuzzySets {
  StandardFuzzySets() {
    super();
  }

  public static IIntUnaryFunction IFunction(int a, int b) {
    return new IIntUnaryFunction() {
      @Override
      public double valueAt(int x) {
        if (x < a) {
          return 1.0;
        } else if (a <= x && x < b) {
          return (double) (b - x) / (b - a);
        } else {
          return 0.0;
        }
      };
    };
  }

  public static IIntUnaryFunction gammaFunction(int a, int b) {
    return new IIntUnaryFunction() {
      @Override
      public double valueAt(int x) {
        if (x < a) {
          return 0;
        } else if (a <= x && x < b) {
          return (double) (x - a) / (b - a);
        } else {
          return 1;
        }
      }
    };
  };

  public static IIntUnaryFunction lambdaFunction(int a, int b, int y) {
    return new IIntUnaryFunction() {

      @Override
      public double valueAt(int x) {
        if (a <= x && x < b) {
          return (double)(x - a)/(b - a);
        } else if (b <= x && x < y) {
          return (double)(y - x)/(y - b);
        } else {
          return 0;
        }
      };
    };
  }

}
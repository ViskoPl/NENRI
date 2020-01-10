package zad2;

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
          return (b - x) / (b - a);
        } else if (x >= b) {
          return 0.0;
        }

        return 0.0;
      };
    };
  }

  public static IIntUnaryFunction gammaFunction(int a, int b) {
    return new IIntUnaryFunction() {
      @Override
      public double valueAt(int x) {
        if (x < a) {
          return 0.0;
        } else if (a <= x && x < b) {
          return (x - a) / (b - a);
        } else if (x >= b) {
          return 1.0;
        }

        return 0.0;
      }
    };
  };

  public static IIntUnaryFunction lambdaFunction(int a, int b, int y) {
    return new IIntUnaryFunction() {

      @Override
      public double valueAt(int x) {
          if (x < a)  {
            return 0.0;
          } else {
            double z = x;
            if (x >= a && x < b) {
              return (z - a) / (b - a);
            }
            else if (x >= b && x < y) {
              return (y - z) / (y - b);
            } else {
              return 0.0;
            }
          }
      };
    };
  }

}
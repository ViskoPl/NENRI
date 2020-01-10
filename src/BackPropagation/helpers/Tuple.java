package lab5.helpers;

public class Tuple<X,Y> {
    private X x;
    private Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public int getIntX() {
        return Integer.parseInt(x.toString());
    }

    public int getIntY() {
        return Integer.parseInt(y.toString());
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
}

package lab5.graphics;

public interface ISubject {
    void notifyObservers();
    void removeObserver(int index);
    void addObserver(IObserver obs);
    void predict();

}

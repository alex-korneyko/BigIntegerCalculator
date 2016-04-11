package ua.goit.gojava;

public interface Observable {

    void regObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

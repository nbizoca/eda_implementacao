package estruturas.arvoreB;

public interface ArvoreB<T extends Comparable<T>> {
    void insert(T elem);
    BNodePosition<T> search(T elem);
    int height();
    int size();
    void printLevels();
}

package estruturas.fila;

public interface Fila<T> {

    // Adiciona um elemento no final da fila
    void enqueue(T elemento);

    // Remove e retorna o elemento do início da fila
    T dequeue();

    // Apenas retorna (visualiza) o elemento do início da fila, sem remover (equivalente ao top)
    T head();

    // Verifica se a fila está vazia
    boolean isEmpty();

    // Verifica se a fila está cheia
    boolean isFull();
}
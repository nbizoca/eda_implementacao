package estruturas.pilha;

public interface Pilha<T> {

    // Adiciona um elemento no topo
    void push(T elemento);

    // Remove e retorna o elemento do topo
    T pop();

    // Apenas retorna (visualiza) o elemento do topo sem remover
    T top();

    // Verifica se a pilha está vazia
    boolean isEmpty();

    // Verifica se a pilha está cheia
    boolean isFull();
}
package estruturas.listaDupla;

public interface DoubleLinkedList<T> {

    // Verifica se a lista está vazia (retorna true ou false)
    boolean isEmpty();

    // Retorna a quantidade de elementos (nós) atualmente na lista
    int size();

    // Busca um elemento específico na lista e o retorna (ou null se não achar)
    T search(T element);

    // Insere um novo elemento no final da lista
    void insert(T element);

    // Busca e remove um elemento específico da lista
    void remove(T element);

    // Converte a nossa lista encadeada em um Array tradicional do Java
    T[] toArray();
}
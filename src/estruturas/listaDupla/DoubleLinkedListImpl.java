package estruturas.listaDupla;

/**
 * Implementação da Lista Duplamente Encadeada.
 */
public class DoubleLinkedListImpl<T> implements DoubleLinkedList<T> {

    // Aponta para o PRIMEIRO vagão do trem
    private NoDuplo<T> inicio;

    // A GRANDE NOVIDADE: Aponta para o ÚLTIMO vagão do trem
    private NoDuplo<T> fim;

    // Controlador de tamanho
    private int tamanho;

    /**
     * Construtor: Inicializa a lista vazia.
     */
    public DoubleLinkedListImpl() {
        this.inicio = null;
        this.fim = null;    // Começa sem último vagão
        this.tamanho = 0;
    }

    // --- Métodos utilitários que já podemos implementar direto ---

    @Override
    public int size() {
        return this.tamanho;
    }

    @Override
    public boolean isEmpty() {
        // A lista está vazia se o tamanho for zero (ou se inicio == null)
        return this.tamanho == 0;
    }

    // --- Métodos que vamos implementar a seguir ---

    @Override
    public void insert(T elemento) {
        // Será implementado a seguir
    }

    @Override
    public void remove(T elemento) {
        // Será implementado a seguir
    }

    @Override
    public T search(T elemento) {
        // Será implementado a seguir
        return null;
    }

    @Override
    public T[] toArray() {
        // Será implementado a seguir
        return null;
    }
}
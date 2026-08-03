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

    @Override
    public int size() {
        return this.tamanho;//verificar o tamanho da lista
    }
    //verificar se está vazia
    @Override
    public boolean isEmpty() {
        // A lista está vazia se o tamanho for zero (ou se inicio == null)
        return this.tamanho == 0;
    }
    /**
     * Insere um novo elemento no final da lista.
     * Graças ao ponteiro 'fim',a operação tem complexidade O(1).
     */
    @Override
    public void insert(T elemento) {
        // 1. Fabricamos o novo vagão com a carga
        NoDuplo<T> novoNo = new NoDuplo<>(elemento);

        // 2. Cenário 1: O trem está vazio
        if (isEmpty()) {
            this.inicio = novoNo;
            this.fim = novoNo; // Ele é o primeiro e o último!
        }
        // 3. Cenário 2: O trem já tem vagões (vamos inserir no final)
        else {
            // O vagão que era o último agora tem o novo vagão como seu "próximo"
            this.fim.setProximo(novoNo);

            // O engate traseiro (anterior) do novo vagão se conecta no antigo último vagão
            novoNo.setAnterior(this.fim);

            // A estação atualiza seu registro: o novo vagão é oficialmente o último
            this.fim = novoNo;
        }

        // 4. Aumentamos o tamanho do trem
        this.tamanho++;
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
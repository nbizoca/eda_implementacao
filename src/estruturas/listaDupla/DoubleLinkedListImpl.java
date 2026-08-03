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
    /**
     * Busca um elemento específico na lista.
     * A complexidade é O(n), pois no pior dos casos precisamos percorrer toda a lista.
     * @param elemento A informação a ser procurada.
     * @return O próprio elemento se encontrado, ou null se não existir na lista.
     */
    @Override
    public T search(T elemento) {
        // 1. O inspetor começa a busca pelo primeiro vagão
        NoDuplo<T> atual = this.inicio;

        // 2. Enquanto o inspetor não cair do trem (chegar no final)
        while (atual != null) {

            // 3. Verifica se a carga deste vagão é exatamente a que procuramos
            // Lembre-se: usamos .equals() para comparar o CONTEÚDO de objetos
            if (atual.getDado().equals(elemento)) {
                return atual.getDado(); // Achou! Retorna a carga.
            }

            // 4. Se não era a carga certa, o inspetor anda para o vagão da FRENTE
            atual = atual.getProximo();
        }

        // 5. Se o while terminou e o método não retornou nada, é porque a carga não está no trem
        return null;
    }
    /**
     * Remove a primeira ocorrência de um elemento específico da lista.
     */
    @Override
    public void remove(T elemento) {
        // Se a lista está vazia, não há o que remover
        if (isEmpty()) {
            return;
        }

        // 1. Mandamos o inspetor achar o vagão
        NoDuplo<T> atual = this.inicio;
        while (atual != null && !atual.getDado().equals(elemento)) {
            atual = atual.getProximo();
        }

        // Se chegou ao final e não achou, o elemento não está na lista
        if (atual == null) {
            return;
        }

        // 2. Cenário 1: É o único vagão do trem
        if (this.inicio == this.fim) {
            this.inicio = null;
            this.fim = null;
        }
        // 3. Cenário 2: É o primeiro vagão (mas tem outros depois)
        else if (atual == this.inicio) {
            this.inicio = atual.getProximo(); // O segundo passa a ser o primeiro
            this.inicio.setAnterior(null);    // Corta a ligação com o vagão removido
        }
        // 4. Cenário 3: É o último vagão
        else if (atual == this.fim) {
            this.fim = atual.getAnterior();   // O penúltimo passa a ser o último
            this.fim.setProximo(null);        // Corta a ligação com o vagão removido
        }
        // 5. Cenário 4: O vagão está no meio
        else {
            NoDuplo<T> vagaoAnterior = atual.getAnterior();
            NoDuplo<T> vagaoProximo = atual.getProximo();

            // O vagão de trás aponta para o da frente
            vagaoAnterior.setProximo(vagaoProximo);

            // O vagão da frente aponta para o de trás
            vagaoProximo.setAnterior(vagaoAnterior);
        }

        // 6. Reduzimos o tamanho oficial do trem
        this.tamanho--;
    }

    /**
     * Converte a Lista Duplamente Encadeada para um Array convencional. Mostra os elementos na ordem atual
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        // Cria um array convencional do tamanho exato da nossa lista
        T[] array = (T[]) new Object[this.tamanho];

        NoDuplo<T> atual = this.inicio;
        int index = 0;

        // Percorre a lista copiando a carga de cada vagão para o array
        while (atual != null) {
            array[index] = atual.getDado();
            atual = atual.getProximo();
            index++;
        }

        return array;
    }
}
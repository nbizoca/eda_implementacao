package estruturas.listaSimples;

public class LinkedListImpl<T> implements LinkedList<T> {

    // Referência apenas para o PRIMEIRO elemento da lista (a "locomotiva")
    // A partir dele, conseguimos chegar em todos os outros
    private No<T> inicio;

    // Guarda a quantidade de elementos para não precisarmos contar do zero
    // toda vez que chamarem o método size()
    private int tamanho;

    /**
     Construtor da lista.
     Quando a lista é criada, ela começa totalmente vazia.
     */
    public LinkedListImpl() {
        this.inicio = null;
        this.tamanho = 0;
    }

    @Override
    public boolean isEmpty() {
        // Se não tem ninguém no "início", significa que a lista inteira está vazia
        return this.inicio == null;
    }

    @Override
    public int size() {
        // Retorna o contador que mantemos atualizado
        return this.tamanho;
    }

    @Override
    public void insert(T elemento) {
        // 1. Criamos a nova caixinha (o novo vagão) com o valor que o usuário passou
        No<T> novoNo = new No<>(elemento);

        // 2. Se a lista estiver vazia, esse novo nó assume o lugar de "início" da lista
        if (isEmpty()) {
            this.inicio = novoNo;
        } else {
            // 3. Se já existem elementos, precisamos encontrar o ÚLTIMO nó para engatar o novo atrás dele.
            // Criamos um "apontador" temporário chamado 'atual' e começamos pelo início da lista
            No<T> atual = this.inicio;

            // Enquanto o nó que estamos olhando tiver um "próximo", nós avançamos para esse próximo
            while (atual.getProximo() != null) {
                atual = atual.getProximo(); // Anda uma casa para frente
            }

            // Quando o laço (while) termina, significa que o 'atual' parou exatamente no último nó.
            // Então, dizemos que o próximo dele passa a ser o nosso 'novoNo'
            atual.setProximo(novoNo);
        }

        // 4. Como adicionamos alguém, aumentamos o tamanho da lista em 1
        this.tamanho++;
    }

    @Override
    public void remove(T elemento) {
        // A ser implementado na próxima etapa...
    }

    @Override
    public T search(T elemento) {
        // A ser implementado na próxima etapa...
        return null;
    }

    @Override
    public T[] toArray() {
        // A ser implementado na próxima etapa...
        return null;
    }
}
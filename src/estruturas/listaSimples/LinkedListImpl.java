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
        this.tamanho++; //Incrementação.
    }
    /**
     * Busca um elemento específico dentro da lista.
     * @param elemento A informação que estamos procurando.
     * @return O elemento encontrado, ou null se ele não existir na lista.
     */
    @Override
    public T search(T elemento) {
        // 1. O "inspetor" começa olhando pelo primeiro vagão
        No<T> atual = this.inicio;

        //Vamos percorrer todos os nós verificando o que tem dentro deles.
        // 2. Enquanto ainda houver vagões para olhar (ou seja, não chegamos ao fim do trem)
        while (atual != null) {

            // 3. Verificamos se a carga deste vagão é igual à que estamos procurando.
            // Atenção: Usamos .equals() e não "==", pois <T> é um Objeto genérico!
            if (atual.getDado().equals(elemento)) {
                return atual.getDado(); // Achamos! Retorna a informação.
            }

            // 4. Se não era a carga certa, o inspetor anda para o próximo vagão
            atual = atual.getProximo();
        }

        // 5. Se o laço de repetição terminar e o código chegar aqui embaixo,
        // significa que olhamos todos os vagões e não encontramos nada.
        return null;
    }

    /**
     * Remove a primeira ocorrência de um elemento específico da lista.
     */
    @Override
    public void remove(T elemento) {
        // 1. Cenário 1: Se a lista estiver vazia, não há o que remover, apenas saímos do método.
        if (isEmpty()) {
            return;
        }

        // 2. Cenário 2: O elemento que queremos remover é exatamente o PRIMEIRO da lista.
        // Verificamos usando o .equals()
        if (this.inicio.getDado().equals(elemento)) {
            // A lista passa a começar a partir do segundo elemento (que pode ser null, se só tivesse um)
            this.inicio = this.inicio.getProximo();
            this.tamanho--; // Diminuímos o tamanho
            return; // Encerramos o método aqui
        }

        // 3. Cenário 3: O elemento está no meio ou no final da lista.
        // Precisamos de dois "apontadores" (inspetores)
        No<T> atual = this.inicio;
        No<T> anterior = null;

        // Vamos caminhando enquanto o 'atual' não for nulo E o dado dele não for igual ao que procuramos
        while (atual != null && !atual.getDado().equals(elemento)) {
            anterior = atual;            // O 'anterior' guarda o vagão de onde estamos saindo
            atual = atual.getProximo();  // O 'atual' avança para o próximo vagão
        }

        // 4. Se o laço terminou e o 'atual' é nulo, significa que chegamos ao fim do trem
        // e não encontramos o elemento. Não fazemos nada.
        if (atual == null) {
            return;
        }

        // 5. Se chegamos aqui, achamos o elemento! O 'atual' é o vagão que queremos remover.
        // Pegamos o vagão 'anterior' e fazemos o engate dele "pular" o vagão atual,
        // ligando direto no vagão que vem DEPOIS do atual.
        anterior.setProximo(atual.getProximo());

        // 6. Por fim, diminuímos o tamanho da lista.
        this.tamanho--;
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
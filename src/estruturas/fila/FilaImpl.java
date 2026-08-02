package estruturas.fila;

public class FilaImpl<T> implements Fila<T> {

    // Nossos atributos fundamentais para a Fila
    private T[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    // O nosso "create" da Fila
    @SuppressWarnings("unchecked")
    public FilaImpl(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade da fila deve ser maior que zero.");
        }
        this.capacidade = capacidade;
        this.elementos = (T[]) new Object[capacidade]; // Criação do array genérico e o casting.
        // Configuração inicial da fila vazia
        this.inicio = 0;   // O primeiro a sair estará no índice 0
        this.fim = -1;     // O fim começa em -1 (pois ainda não inserimos nada)
        this.tamanho = 0;  // Obviamente, começamos com zero elementos
    }

    @Override
    public boolean isEmpty() {
        return this.tamanho == 0;//Vai ser vazia se a fila tem tamanho 0.
    }

    @Override
    public boolean isFull() {
        return this.tamanho == this.capacidade;//Vai ser cheia se o tamanho da fila atinge a capacidade.
    }
    @Override
    public void enqueue(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("Erro: A fila está cheia! (Queue Overflow)");
        }

        // Se (fim + 1) for igual à capacidade, o resto da divisão será 0, fazendo o índice voltar para o início.
        this.fim = (this.fim + 1) % this.capacidade;

        // Adiciona o elemento na nova posição do fim
        this.elementos[this.fim] = elemento;

        // Aumenta a contagem de pessoas na fila
        this.tamanho++;
    }

}
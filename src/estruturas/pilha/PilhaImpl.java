package estruturas.pilha;

public class PilhaImpl<T> implements Pilha<T> {

    // 3 atributos fundamentais (encapsulados)
    private T[] elementos;
    private int topo;
    private int capacidade;

    // "create" exigido no projeto
    @SuppressWarnings("unchecked")//Só para o intellij não configurar como erro.
    public PilhaImpl(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade da pilha deve ser maior que zero.");
        }
        this.capacidade = capacidade;
        // Criamos um array de Object e "disfarçamos" de array tipo T
        this.elementos = (T[]) new Object[capacidade];

        // O topo começa em -1 porque o array em Java começa no índice 0.
        // -1 significa que não há nenhum elemento apontado ainda e a pilha está vazia.
        this.topo = -1;
    }
    //Método para verificar se está vazia
    @Override
    public boolean isEmpty() {
        return this.topo == -1;
    }
    //Método para verificar se está cheia.
    @Override
    public boolean isFull() {
        // Se a capacidade for 10, o último índice válido é o 9.
        return this.topo == this.capacidade - 1;
    }

    @Override
    public void push(T elemento) {
        if (isFull()) {
            // IllegalStateException é a exceção ideal para quando o estado da estrutura não permite a ação
            throw new IllegalStateException("Erro: A pilha está cheia! (Stack Overflow)");
        }

        // Incrementa o índice do topo e insere o elemento
        this.topo++;
        this.elementos[this.topo] = elemento;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Erro: A pilha está vazia! (Stack Underflow)");
        }
        // Salva o elemento que está no topo antes de mexer nos ponteiros
        T elementoRemovido = this.elementos[this.topo];
        //anular a referência para otimizar o uso da memória.
        this.elementos[this.topo] = null;
        // Desce o topo para o elemento de baixo
        this.topo--;

        return elementoRemovido;
    }

    @Override
    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("Erro: A pilha está vazia! Não há elementos para visualizar.");
        }
        // Apenas retorna o elemento, sem decrementar o topo ou anular a referência
        return this.elementos[this.topo];
    }

}
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


}


}
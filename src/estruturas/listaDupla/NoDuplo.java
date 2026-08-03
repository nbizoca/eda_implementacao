package estruturas.listaDupla;

public class NoDuplo<T> {

    // A "carga" transportada pelo vagão
    private T dado;

    // O engate que aponta para o vagão da FRENTE
    private NoDuplo<T> proximo;

    //O ponteiro que aponta para o vagão de TRÁS, elemento anterior.
    private NoDuplo<T> anterior;

    /**
     * Construtor: Chamado toda vez que criamos um novo Nó Duplo.
     */
    public NoDuplo(T dado) {
        this.dado = dado;
        // Quando um vagão é construído, seus engates ainda estão soltos
        this.proximo = null;
        this.anterior = null;
    }

    // --- Getters e Setters ---

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public NoDuplo<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoDuplo<T> proximo) {
        this.proximo = proximo;
    }

    // Novos métodos para gerenciar o engate traseiro
    public NoDuplo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDuplo<T> anterior) {
        this.anterior = anterior;
    }
}
package estruturas.listaSimples;

public class No<T> {

    // A Classe que representa um elemento (Nó) da nossa Lista Encadeada.
    // Pense nela como um "vagão de trem" que carrega uma carga e pode se ligar a outro vagão.
    private T dado;

    // O "engate" que aponta para o proximo vagão da lista
    private No<T> proximo;

    //Chamamos o construtor sempre que for para criar um novo nó
    public No(T dado) {
        this.dado = dado;
        // Quando um nó nasce, ele é o último da fila, então ainda não tem um "próximo"
        this.proximo = null;
    }

    // --- Getters e Setters (Para acessar e modificar os valores privados) ---

    // Retorna o dado guardado neste nó
    public T getDado() {
        return dado;
    }

    // Altera o dado guardado neste nó
    public void setDado(T dado) {
        this.dado = dado;
    }

    // Retorna quem é o próximo nó (o próximo vagão)
    public No<T> getProximo() {
        return proximo;
    }

    // Define quem será o próximo nó (engata um novo vagão atrás deste)
    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
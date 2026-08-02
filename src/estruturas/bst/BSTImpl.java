package estruturas.bst;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BT<T> {
    private BSTNode<T> root;

    public BSTImpl(){
        this.root = null;
    }

    // procura o maior valor da árvore (o mais a direita)
    public BSTNode<T> maximum(BSTNode<T> no){
        if(no == null){
            return null;
        }

        BSTNode<T> atual = no;

        while (atual.getRight() != null){
            atual = atual.getRight();
        }

        return atual;
    }

    // procura o menor valor da árvore (o mais a esquerda)
    public BSTNode<T> minimum(BSTNode<T> no){
         if(no == null){
             return null;
         }

         BSTNode<T> atual = no;

         while (atual.getLeft() != null){
             atual = atual.getLeft();
         }

         return atual;
    }

    public BSTNode<T> successor(BSTNode<T> no){
        if (no == null){
            return null;
        }

        // se tiver filho direito, vai ser o menor valor da direita
        if(no.getRight() != null){
            return minimum(no.getRight());
        }

        // se não tiver filho direito, vai subindo na árvore enquanto o nó atual for filho direito
        // para o loop quando o nó atual for filho esquerdo
        BSTNode<T> atual = no;
        BSTNode<T> parent = atual.getParent();

        while(parent != null && atual == parent.getRight()){
            atual = parent;
            parent = parent.getParent();
        }

        return parent;

    }

    public BSTNode<T> predecessor(BSTNode<T> no){
        if (no == null){
            return null;
        }

        // se tiver filho esquerdo, vai ser o maior valor a esquerda
        if (no.getLeft() != null){
            return maximum(no.getLeft());
        }

        // se não tiver filho esquerdo, vai subindo na árvore enquanto o nó atual for filho esquerdo

        BSTNode<T> atual = no;
        BSTNode<T> parent = atual.getParent();

        while(parent != null && atual == parent.getLeft()){
            atual = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    @Override
    public BSTNode<T> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int height() {
        return height(root);
    }

    // calcula a altura da árvore de acordo com a quantidade de níveis
    private int height(BSTNode<T> no){
        if (no.isEmpty()){
            return 0;
        }

        int alturaEsquerda = height(no.getLeft());
        int alturaDireita = height(no.getRight());

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    @Override
    public BSTNode<T> search(T elem) {
        BSTNode<T> atual = root; // inicia atual com o valor da raiz
        while (atual != null && elem.compareTo(atual.getData()) != 0){ //enquanto tiver nó e não for o valor da busca
            if (elem.compareTo(atual.getData()) < 0){
                atual = atual.getLeft(); //se for menor, vai pra esquerda
            } else {
                atual = atual.getRight(); //se não, vai pra direita
            }
        }

        return atual;
    }


    @Override
    public void insert(T value) {
        BSTNode<T> noNovo = new BSTNode<>(value);
        BSTNode<T> parent = null;
        BSTNode<T> noAtual = root;

        while(noAtual != null){
            parent = noAtual;
            if (value.compareTo(noAtual.getData())< 0){ // se o valor que vai ser inserido for menor que o nó atual
                noAtual = noAtual.getLeft(); //nó atual é igual o nó esquerdo
            } else {
                noAtual = noAtual.getRight(); // se não, é igual o nó direito
            }
        }

        noNovo.setParent(parent); // atualiza o pai do nó novo

        if(parent == null){ //caso a raiz esteja vazia, insere o novo nó na raiz
            root = noNovo;
        } else if (value.compareTo(parent.getData()) < 0) { // se o valor a ser inserido for menor que o pai, insere na esquerda
            parent.setLeft(noNovo);
        } else {
            parent.setRight(noNovo); //se for maior, insere na direita
        }

    }

    @Override
    public void remove(T key) {
        BSTNode<T> no = new BSTNode<>(key);

        if(no == null){
            return;
        }

        BSTNode<T> noRemovido;
        BSTNode<T> substituto;

        // se possui um ou nenhum filho, o nó a ser removido é o próprio nó
        if (no.getLeft() == null || no.getRight() == null){
            noRemovido = no;
        } else {
            // se tiver 2 filhos, o que será removido é o sucessor(o menor valor a direita)
            noRemovido = successor(no);
        }

        // nó removido vai ter no máx um filho
        if (noRemovido.getLeft() != null) {
            substituto = noRemovido.getLeft(); //se o nó que será removido tiver filho esquerdo, o substituto será esse filho

        } else {
            substituto = noRemovido.getRight(); // se tiver filho direito, vai ser esse
        }

        // atualiza o pai do filho
        if(substituto != null){ //se o substituto não for nulo, o pai dele será o pai do nó que vai ser removido
            substituto.setParent(noRemovido.getParent());
        }

        // se o nó a ser  removido for a raiz, o valor da raiz vai ser o do substituto
        if(noRemovido.getParent() == null){
            root = substituto;
        }

        // se o nó a ser removido for filho esquerdo, coloca o valor do substituto a esquerda
        else if(noRemovido == noRemovido.getParent().getLeft()){
            noRemovido.getParent().setLeft(substituto);
        }

        // se o nó a ser removido for filho direito, coloca o valor do substituto a direita
        else {
            noRemovido.getParent().setRight(substituto);
        }

        // se o nó tinha 2 filhos, removeu o sucessor
        // copia o valor do sucessor para o nó
        if (noRemovido != no){
            no.setData(noRemovido.getData());
        }

    }

    @Override
    public T[] preOrder() {
        if(root == null){
            return(T[]) new Comparable[0]; //retorna um array vazio se a árvore não tem raiz
        }

        @SuppressWarnings("unchecked") //para sair o aviso de unchecked cast por conta da conversão
        T[] elementos = (T[]) Array.newInstance( // cria um array com o tamanho da arvore
                root.getData().getClass(), size()
        );

        int[] posicao = {0}; //controla o índice que o elemento vai ser colocado
        preOrder(root, elementos, posicao);
        return elementos;
    }

    private void preOrder(BSTNode<T> no, T[] elementos, int[] posicao){
        if (no != null){
            elementos[posicao[0]] = no.getData(); //guarda o valor do nó atual
            posicao[0] ++;

            preOrder(no.getLeft(), elementos, posicao); //vai pela direita
            preOrder(no.getRight(), elementos, posicao); //vai pela esquerda
        }
    }

    @Override
    public T[] order() {
        if(root == null){
            return(T[]) new Comparable[0];
        };

        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(
                root.getData().getClass(),
                size()
        );

        int[] posicao = {0};

        order(root, elementos, posicao);

        return elementos;
    }

    private void order(BSTNode<T> no, T[] elementos, int[] posicao){
        if (no != null){
            order(no.getLeft(), elementos, posicao); //percorre a esquerda primeiro

            elementos[posicao[0]] = no.getData(); // salva o nó atual
            posicao[0]++;

            order(no.getRight(), elementos, posicao); //percorre a direita
        }
    }

    @Override
    public T[] postOrder() {
        if (root == null) {
            return (T[]) new Comparable[0];
        }

        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(
                root.getData().getClass(),
                size()
        );

        int[] posicao = {0};

        postOrder(root, elementos, posicao);

        return elementos;
    }

    private void postOrder(
            BSTNode<T> no,
            T[] elementos,
            int[] posicao
    ) {
        if (no != null) {
            postOrder(no.getLeft(), elementos, posicao); //percorre esquerda
            postOrder(no.getRight(), elementos, posicao); //percorre direita

            elementos[posicao[0]] = no.getData(); //salva nó atual
            posicao[0]++;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    // calcula o tamanho da árvore
    private int size(BSTNode<T> no){
        if(no.isEmpty()){
            return 0;
        } else {
            return 1 + size(no.getLeft())+ size(no.getRight());
        }

    }
}

package estruturas.avl;

import estruturas.bst.BSTImpl;
import estruturas.bst.BSTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

    @Override
    public void insert(T elem) {
        if(elem == null){
            return;
        }

        // se a árvore tiver vazia, a raiz vai ser igual o valor do novo nó
        if (root == null){
            root = new BSTNode<>(elem);
            return;
        }

        BSTNode<T> noAtual = root;
        BSTNode<T> parent = null;

        //pesquisa a posição pra inserir o novo elemento
        while(noAtual != null){
            parent = noAtual;

            if(elem.compareTo(noAtual.getData()) < 0){
                noAtual = noAtual.getLeft();
            } else if (elem.compareTo(noAtual.getData()) > 0) {
                noAtual = noAtual.getRight();
            } else {
                return;
            }
        }

        BSTNode<T> novoNo = new BSTNode<>(elem);
        novoNo.setParent(parent);

        if(elem.compareTo(parent.getData()) < 0){
            parent.setLeft(novoNo);
        } else {
            parent.setRight(novoNo);
        }

        //faz o balanceamento
        rebalanceUp(parent);
    }

    @Override
    public void remove(T elem) {
        BSTNode<T> paraRemover = search(elem);

        if(paraRemover == null){
            return;
        }

        //nó com dois filhos (copia o valor do sucessor e remove ele)
        if(paraRemover.getLeft() != null && paraRemover.getRight() != null){
            BSTNode<T> sucessor = successor(paraRemover);

            paraRemover.setData(sucessor.getData());
            paraRemover = sucessor;
        }

        BSTNode<T> substituto;
        if (paraRemover.getLeft() != null){
            substituto = paraRemover.getLeft();
        } else {
            substituto = paraRemover.getRight();
        }

        BSTNode<T> parent = paraRemover.getParent();

        // atualiza o pai do que vai substituir o no removido
        if(substituto != null){
            substituto.setParent(parent);
        }

        // se for a raiz
        if(parent == null){
            root = substituto;
        }

        // se for o filho esquerdo
        else if(paraRemover == parent.getLeft()){
            parent.setLeft(substituto);
        }

        else {
            parent.setRight(substituto);
        }

        rebalanceUp(parent);
    }

    // calcula o FB
    private int calculateBalance(BSTNode<T> no) {
        if(no == null){
            return 0;
        }

        return height(no.getLeft()) - height(no.getRight());
    }

    private void rebalance(BSTNode<T> no) {
        if (no == null) {
            return;
        }

        int balance = calculateBalance(no);

        //subarvore esquerda com mais níveis que a direita
        if (balance > 1) {
            int leftChildBalance = calculateBalance(no.getLeft());

            //caso left-right: filho esquerdo pesa pra direita
            if (leftChildBalance < 0) {
                leftRotation(no.getLeft()); //rotação à esquerda
            }

            //roda pra direita
            rightRotation(no);
        }

        //se pesa para a direita
        else if (balance < -1) {
            int rightChildBalance = calculateBalance(no.getRight());

            //caso left-right
            if (rightChildBalance > 0) {
                rightRotation(no.getRight()); //roda pra direita
            }

            //roda pra esquerda
            leftRotation(no);
        }
    }

    private void rebalanceUp(BSTNode<T> no) {
        BSTNode<T> noAtual = no;

        while (noAtual != null) {
            BSTNode<T> parentBeforeRotation = noAtual.getParent(); //guarda o pai antes da rotação

            rebalance(noAtual);
            noAtual = parentBeforeRotation; //continua subindo na árvore
        }
    }

    private void leftRotation(BSTNode<T> no) {
        //pra rotacionar a esquerda, tem que ter filho direito
        if (no == null || no.getRight() == null) {
            return;
        }

        BSTNode<T> pivot = no.getRight(); //filho direito
        BSTNode<T> transferredSubtree = pivot.getLeft(); //subarvore esquerda do pivo
        BSTNode<T> oldParent = no.getParent(); //guarda o pai antigo

        //filho esquerdo do pivo vai ser o filho direito do nó original
        no.setRight(transferredSubtree);

        //atualiza o pai
        if (transferredSubtree != null) {
            transferredSubtree.setParent(no);
        }

        //nó vira filho esquerdo do pivo
        pivot.setLeft(no);
        no.setParent(pivot);

        //pivo vai pra posicao que era do nó
        pivot.setParent(oldParent);

        if (oldParent == null) {
            root = pivot; //se nao tiver pai, pivo vira raiz
        } else if (oldParent.getLeft() == no) {
            oldParent.setLeft(pivot); //se o no era filho esquerdo, pivo vai pra esquerda
        } else {
            oldParent.setRight(pivot);
        }
    }

    private void rightRotation(BSTNode<T> no) {
        if (no == null || no.getLeft() == null) {
            return;
        }

        BSTNode<T> pivot = no.getLeft();
        BSTNode<T> transferredSubtree = pivot.getRight();
        BSTNode<T> oldParent = no.getParent();

        no.setLeft(transferredSubtree);

        if (transferredSubtree != null) {
            transferredSubtree.setParent(no);
        }

        pivot.setRight(no);
        no.setParent(pivot);

        pivot.setParent(oldParent);

        if (oldParent == null) {
            root = pivot;
        } else if (oldParent.getLeft() == no) {
            oldParent.setLeft(pivot);
        } else {
            oldParent.setRight(pivot);
        }
    }

}



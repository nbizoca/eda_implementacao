package estruturas.arvoreB;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBImpl<T extends Comparable<T>> implements ArvoreB<T> {
    private BNode<T> root;
    private final int order;

    public ArvoreBImpl(int order){
        if(order < 3){
            throw new IllegalArgumentException("A ordem deve ser pelo menos 3!");
        }

        this.order = order;
        this.root = null;
    }

    @Override
    public void insert(T elem) {
        if(elem == null){
            throw new IllegalArgumentException("Não é possível inserir elemento nulo!");
        }

        if (root == null){
            root = new BNode<>(order);
            root.elements.add(elem);
        }

        BNode<T> noAtual = root;

        while(!noAtual.children.isEmpty()){
            int position = findPosition(noAtual, elem);

            if (position < noAtual.elements.size() && elem.compareTo(noAtual.elements.get(position)) == 0){
                return;
            }

            noAtual = noAtual.children.get(position);
        }

        int insertionPosition = findPosition(noAtual, elem);

        if(insertionPosition < noAtual.elements.size() && elem.compareTo(noAtual.elements.get(insertionPosition)) == 0){
            return;
        }

        noAtual.elements.add(insertionPosition, elem);
    }

    @Override
    public BNodePosition search(T elem) {
        if(elem == null){
            return new BNodePosition<>();
        }

        BNode<T> noAtual = root;

        while(noAtual != null){
            int position = findPosition(noAtual, elem);

            if (position < noAtual.elements.size()
                    && elem.compareTo(
                    noAtual.elements.get(position)
            ) == 0) {

                return new BNodePosition<>(noAtual, position);
            }

            if(noAtual.children.isEmpty()){
                return new BNodePosition<>();
            }

            noAtual = noAtual.children.get(position);
        }

        return new BNodePosition<>();
    }

    private void split(BNode<T> no){
        int medianPosition = no.elements.size() / 2;

        T newParent = no.elements.get(medianPosition);

        BNode<T> rightNode = new BNode<>(order);

        while(no.elements.size() > medianPosition + 1){
            T elemMovido = no.elements.remove(medianPosition + 1);
            rightNode.elements.add(elemMovido);
        }

        no.elements.remove(medianPosition);

        while(no.children.size() > medianPosition + 1){
            BNode<T> filhoMovido = no.children.remove(medianPosition + 1);
            rightNode.children.add(filhoMovido);
            filhoMovido.parent = rightNode;
        }

        if(no.parent == null){
            BNode<T> novaRaiz = new BNode<> (order);
            novaRaiz.elements.add(newParent);
            novaRaiz.children.add(no);
            novaRaiz.parent = novaRaiz;

            root = novaRaiz;
            return;
        }

        BNode<T> paiAtual = no.parent;
        int nodePosition = paiAtual.children.indexOf(no);

        paiAtual.children.add(nodePosition + 1, rightNode);
        rightNode.parent = paiAtual;

        if(paiAtual.elements.size() > paiAtual.maxKeys){
            split(paiAtual);
        }
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BNode<T> no){
        if (no == null){
            return 0;
        }

        if(no.children.isEmpty()){
            return 1;
        }

        int maiorFilho = 0;

        for(BNode<T> child : no.children){
            int alturaFilho = height(child);

            if(alturaFilho > maiorFilho){
                maiorFilho = alturaFilho;
            }
        }

        return 1 + maiorFilho;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BNode<T> no){
        if(no == null){
            return 0;
        }

        int totalElem = no.elements.size();

        for(BNode<T> child: no.children){
            totalElem += size(child);
        }

        return totalElem;
    }

    @Override
    public void printLevels() {
        if(root == null){
            System.out.println("A árvore está vazia!");
        }

        Queue<BNode<T>> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;

        while (!queue.isEmpty()){
            int nosNivelAtual = queue.size();

            System.out.println("Nível: " + level + ": ");
            for(int i = 0; i < nosNivelAtual; i++){
                BNode<T> noAtual = queue.remove();

                System.out.println(noAtual.elements);

                if(i < nosNivelAtual -1){
                    System.out.println(" | ");
                }

                queue.addAll(noAtual.children);
            }

            System.out.println();
            level++;
        }
    }

    private int findPosition(BNode<T> no, T elem) {
        int position = 0;

        while (position < no.elements.size()
                && elem.compareTo(
                no.elements.get(position)
        ) > 0) {

            position++;
        }

        return position;
    }

    public BNode<T> getRoot() {
        return root;
    }

    public void setRoot(BNode<T> root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }
}

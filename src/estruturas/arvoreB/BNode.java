package estruturas.arvoreB;

import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
    LinkedList<T> elements;
    LinkedList<BNode<T>> children;
    BNode<T> parent;
    int maxKeys;
    int maxChildren;

    public BNode(int order){
        this.maxChildren = order;
        this.maxKeys = order -1;
        this.elements = new LinkedList<T>();
        this.children = new LinkedList<BNode<T>>();
    }
}

package estruturas.avl;

import estruturas.bst.BSTNode;
import estruturas.bst.BT;

public interface AVLTree<T extends Comparable<T>> extends BT<T> {

    @Override
    public void insert(T element);

    @Override
    public void remove(T element);

}

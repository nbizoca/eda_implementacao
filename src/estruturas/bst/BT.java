package estruturas.bst;

public interface BT <T>{
    public BSTNode<T> getRoot();
    public boolean isEmpty();
    public int height();
    public BSTNode<T> search(T elem);
    public void insert(T value);
    public void remove(T key);
    public T[] preOrder();
    public T[] order();
    public T[] postOrder();
    public int size();
}

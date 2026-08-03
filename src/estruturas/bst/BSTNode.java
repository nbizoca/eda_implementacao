package estruturas.bst;

public class BSTNode<T> {
    protected T data;
    protected BSTNode<T> left;
    protected BSTNode<T> right;
    protected BSTNode<T> parent;

    public BSTNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public boolean isEmpty(){
        return this.data == null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public BSTNode<T> getParent() {
        return parent;
    }

    public void setParent(BSTNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "BTNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}
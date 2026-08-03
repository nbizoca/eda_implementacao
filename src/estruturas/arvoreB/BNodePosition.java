package estruturas.arvoreB;

public class BNodePosition <T extends Comparable<T>>{

    private BNode<T> node;
    private int position;

    public BNodePosition(BNode<T> node, int position){
        this.node = node;
        this.position = position;
    }

    public BNodePosition(){
        this.node = null;
        this.position = -1;
    }

    public BNode<T> getNode() {
        return node;
    }

    public void setNode(BNode<T> node) {
        this.node = node;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
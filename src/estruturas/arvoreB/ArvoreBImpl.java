package estruturas.arvoreB;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBImpl<T extends Comparable<T>> implements ArvoreB<T> {
    private BNode<T> root;
    private final int order;

    public ArvoreBImpl(int order) {
        if (order < 3) {
            throw new IllegalArgumentException(
                    "A ordem deve ser pelo menos 3!"
            );
        }

        this.order = order;
        this.root = null;
    }

    // faz a inserção, sempre em um nó folha
    @Override
    public void insert(T elem) {


        //verificação para não inserir nulos
        if (elem == null) {
            throw new IllegalArgumentException(
                    "Não é possível inserir elemento nulo!"
            );
        }

        //se tiver vazia, insere o elemento na raiz
        if (root == null) {
            root = new BNode<>(order);
            root.elements.add(elem);

            return;
        }

        //busca para encontrar a folha para inserção, a partir da raiz

        BNode<T> noAtual = root;

        //enquanto o nó atual tiver filhos, vai procurando
        while (!noAtual.children.isEmpty()) {

            //acha a posição do filho que deve ser acessado
            int position = findPosition(noAtual, elem);

            //se o elemento já existe, não insere ele, pra não ter repetidos
            if (position < noAtual.elements.size() && elem.compareTo(noAtual.elements.get(position)) == 0) {
                return;
            }

            //agora o nó atual é o filho da posição encontrada
            noAtual = noAtual.children.get(position);
        }

        //agora que nó atual é folha, descobre a posição que ele deve ser inserido
        int insertionPosition = findPosition(noAtual, elem);

        // verifica se o elemento já existe dentro da folha
        if (insertionPosition < noAtual.elements.size() && elem.compareTo(noAtual.elements.get(insertionPosition)) == 0) {
            return;
        }

        //insere o elemento na posição e desloca os outros para a direita
        noAtual.elements.add(insertionPosition, elem);

        // se o nó passou da quantidade de máxima de chaves, é dividido
        if (noAtual.elements.size() > noAtual.maxKeys) {
            split(noAtual);
        }
    }

    @Override
    public BNodePosition<T> search(T elem) {

        if (elem == null) {
            return new BNodePosition<>();
        }

        // começa a busca pela raiz
        BNode<T> noAtual = root;

        while (noAtual != null) {
            //procura a posição do elemento dentro do nó atual
            int position = findPosition(noAtual, elem);

            //se existir a posição e a chave for igual a elem, então encontrou
            if (position < noAtual.elements.size() && elem.compareTo(noAtual.elements.get(position)) == 0) {
                return new BNodePosition<>(noAtual, position);
            }

            // se chegar em uma folha e ainda não tiver encontrado, então ele não existe
            if (noAtual.children.isEmpty()) {
                return new BNodePosition<>();
            }

            noAtual = noAtual.children.get(position);
        }

        return new BNodePosition<>();
    }

    private void split(BNode<T> no) {

        // encontra o índice do elemento do meio
        int medianPosition = no.elements.size() / 2;

        // o pai agora é o elemento do meio
        T elementoPromovido = no.elements.get(medianPosition);

        // cria o novo nó que vai ficar na direita
        BNode<T> noDireito = new BNode<>(order);

        // para mover os elementos depois do meio para a direita
        while (no.elements.size() > medianPosition + 1) {
            T elementoMovido = no.elements.remove(medianPosition + 1);
            noDireito.elements.add(elementoMovido);
        }

        //remove o elemento do meio, que agora vai ser o pai
        no.elements.remove(medianPosition);

        // move os filhos maiores que o meio pra a direita
        while (no.children.size() > medianPosition + 1) {
            BNode<T> filhoMovido = no.children.remove(medianPosition + 1);
            noDireito.children.add(filhoMovido);

            //atualiza o pai do filho que foi movido
            filhoMovido.parent = noDireito;
        }

        //se o nó dividido for a raiz, então cria uma nova raiz
        if (no.parent == null) {
            BNode<T> novaRaiz = new BNode<>(order);

            //a media vira a chave da nova raiz
            novaRaiz.elements.add(elementoPromovido);

            // o nó original fica como filho esquerdo
            novaRaiz.children.add(no);

            // o novo nó fica sendo filho direito
            novaRaiz.children.add(noDireito);

            //atualiza o pai dos nós pra nova raiz
            no.parent = novaRaiz;
            noDireito.parent = novaRaiz;

            root = novaRaiz;

            return;
        }

        // se o nó dividido não era raiz então encontramos o pai dele
        BNode<T> paiAtual = no.parent;

        //descbre a posicao do no original dentro dos filhos
        int nodePosition = paiAtual.children.indexOf(no);

        //insere a media na posição do nó original
        paiAtual.elements.add(nodePosition, elementoPromovido);

        //adiciona o novo nó direito
        paiAtual.children.add(nodePosition + 1, noDireito);


         // define o pai do novo nó direito
        noDireito.parent = paiAtual;

        // divide o pai se tiver ultrapassado o maximo de chaves
        if (paiAtual.elements.size() > paiAtual.maxKeys) {
            split(paiAtual);
        }
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BNode<T> no) {
        if (no == null) {
            return 0;
        }

        //um nó folha é igual um nível
        if (no.children.isEmpty()) {
            return 1;
        }

        int maiorAlturaFilho = 0;

        //calcula a altura de cada subárvore filha
        for (BNode<T> child : no.children) {
            int alturaFilho = height(child);

            if (alturaFilho > maiorAlturaFilho) {
                maiorAlturaFilho = alturaFilho;
            }
        }

        return 1 + maiorAlturaFilho;
    }

    /*
     * Retorna a quantidade total de elementos da árvore.
     *
     * Não conta a quantidade de nós/páginas.
     * Conta todas as chaves armazenadas.
     */
    @Override
    public int size() {
        return size(root);
    }

     //conta a quantidade de chaves da arvore
    private int size(BNode<T> no) {
        if (no == null) {
            return 0;
        }

        //quantidade de elementos do nó atual
        int totalElementos = no.elements.size();

        //soma os elementos de todos os filhos
        for (BNode<T> child : no.children) {
            totalElementos += size(child);
        }
        return totalElementos;
    }

    //imprime a árvore nível por nível
    @Override
    public void printLevels() {
        if (root == null) {
            System.out.println("A árvore está vazia!");
            return;
        }

        //fila pra guardar os nós que ainda vão ser imprimidos
        Queue<BNode<T>> queue = new LinkedList<>();

        queue.add(root);

        int level = 1;

        while (!queue.isEmpty()) {
            //guarda quantos nós tem no nivel atual
            int nosNivelAtual = queue.size();

            System.out.print("Nível " + level + ": ");

            //percorre os nós do nivel atual
            for (int i = 0; i < nosNivelAtual; i++) {

                //remove o primeiro no da fila
                BNode<T> noAtual = queue.remove();

                 // imprime as chaves da página
                System.out.print(noAtual.elements);

                //imprime um separador pros nós
                if (i < nosNivelAtual - 1) {
                    System.out.print(" | ");
                }

                //coloca os filhos na fila
                queue.addAll(noAtual.children);
            }

            System.out.println();
            level++;
        }
    }

    //método pra encontrar a posição de um elemento
    private int findPosition(BNode<T> no, T elem) {
        int position = 0;

        // incrementa a posição enquanto o elemento for maior que a chave
        while (position < no.elements.size() && elem.compareTo(no.elements.get(position)) > 0) {
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
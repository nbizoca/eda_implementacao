package app;

import estruturas.avl.AVLTreeImpl;
import estruturas.bst.BSTNode;

import java.util.Arrays;
import java.util.Scanner;

public class MenuAVL {

    private final Scanner scanner;

    public MenuAVL(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    inserir(arvore);
                    break;

                case 2:
                    remover(arvore);
                    break;

                case 3:
                    pesquisar(arvore);
                    break;

                case 4:
                    exibirPercursos(arvore);
                    break;

                case 5:
                    System.out.println(
                            "\nAltura da árvore: " + arvore.height()
                    );
                    break;

                case 6:
                    System.out.println(
                            "\nQuantidade de nós: " + arvore.size()
                    );
                    break;

                case 7:
                    demonstrarLL();
                    break;

                case 8:
                    demonstrarRR();
                    break;

                case 9:
                    demonstrarLR();
                    break;

                case 10:
                    demonstrarRL();
                    break;

                case 11:
                    demonstrarRemocao();
                    break;

                case 0:
                    System.out.println(
                            "\nVoltando ao menu principal..."
                    );
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

            if (opcao != 0) {
                pausar();
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Árvore AVL");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1  - Inserir elemento");
        System.out.println();
        System.out.println("2  - Remover elemento");
        System.out.println();
        System.out.println("3  - Pesquisar elemento");
        System.out.println();
        System.out.println("4  - Exibir percursos");
        System.out.println();
        System.out.println("5  - Consultar altura");
        System.out.println();
        System.out.println("6  - Consultar tamanho");
        System.out.println();
        System.out.println("7  - Demonstrar rotação LL");
        System.out.println();
        System.out.println("8  - Demonstrar rotação RR");
        System.out.println();
        System.out.println("9  - Demonstrar rotação LR");
        System.out.println();
        System.out.println("10 - Demonstrar rotação RL");
        System.out.println();
        System.out.println("11 - Demonstrar remoção");
        System.out.println();
        System.out.println("0  - Voltar ao menu principal");
        System.out.println();
    }

    private void inserir(AVLTreeImpl<Integer> arvore) {
        int elemento = lerInteiro(
                "Digite o elemento que será inserido: "
        );

        if (arvore.search(elemento) != null) {
            System.out.println(
                    "\nO elemento já existe na árvore."
            );
            return;
        }

        arvore.insert(elemento);

        System.out.println(
                "\nElemento " + elemento + " inserido com sucesso!"
        );

        exibirPercursos(arvore);
    }

    private void remover(AVLTreeImpl<Integer> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("\nA árvore está vazia.");
            return;
        }

        int elemento = lerInteiro(
                "Digite o elemento que será removido: "
        );

        if (arvore.search(elemento) == null) {
            System.out.println(
                    "\nO elemento não existe na árvore."
            );
            return;
        }

        arvore.remove(elemento);

        System.out.println(
                "\nElemento " + elemento + " removido com sucesso!"
        );

        exibirPercursos(arvore);
    }

    private void pesquisar(AVLTreeImpl<Integer> arvore) {
        int elemento = lerInteiro(
                "Digite o elemento que será pesquisado: "
        );

        BSTNode<Integer> resultado = arvore.search(elemento);

        if (resultado == null) {
            System.out.println("\nElemento não encontrado.");
        } else {
            System.out.println(
                    "\nElemento encontrado: " + resultado.getData()
            );
        }
    }

    private void exibirPercursos(AVLTreeImpl<Integer> arvore) {
        System.out.println(
                "\nPré-ordem: "
                        + Arrays.toString(arvore.preOrder())
        );

        System.out.println(
                "Em ordem: "
                        + Arrays.toString(arvore.order())
        );

        System.out.println(
                "Pós-ordem: "
                        + Arrays.toString(arvore.postOrder())
        );
    }

    private void demonstrarLL() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();

        arvore.insert(30);
        arvore.insert(20);
        arvore.insert(10);

        System.out.println();
        System.out.println("Caso LL");
        System.out.println("Elementos inseridos: 30, 20 e 10");
        System.out.println(
                "Pré-ordem após balanceamento: "
                        + Arrays.toString(arvore.preOrder())
        );
    }

    private void demonstrarRR() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();

        arvore.insert(10);
        arvore.insert(20);
        arvore.insert(30);

        System.out.println();
        System.out.println("Caso RR");
        System.out.println("Elementos inseridos: 10, 20 e 30");
        System.out.println(
                "Pré-ordem após balanceamento: "
                        + Arrays.toString(arvore.preOrder())
        );
    }

    private void demonstrarLR() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();

        arvore.insert(30);
        arvore.insert(10);
        arvore.insert(20);

        System.out.println();
        System.out.println("Caso LR");
        System.out.println("Elementos inseridos: 30, 10 e 20");
        System.out.println(
                "Pré-ordem após balanceamento: "
                        + Arrays.toString(arvore.preOrder())
        );
    }

    private void demonstrarRL() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();

        arvore.insert(10);
        arvore.insert(30);
        arvore.insert(20);

        System.out.println();
        System.out.println("Caso RL");
        System.out.println("Elementos inseridos: 10, 30 e 20");
        System.out.println(
                "Pré-ordem após balanceamento: "
                        + Arrays.toString(arvore.preOrder())
        );
    }

    private void demonstrarRemocao() {
        AVLTreeImpl<Integer> arvore = new AVLTreeImpl<>();

        int[] dados = {40, 20, 60, 10, 30, 50, 70, 5};

        for (int elemento : dados) {
            arvore.insert(elemento);
        }

        System.out.println();
        System.out.println(
                "Árvore antes da remoção: "
                        + Arrays.toString(arvore.preOrder())
        );

        arvore.remove(70);

        System.out.println(
                "Depois de remover 70: "
                        + Arrays.toString(arvore.preOrder())
        );
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Entrada inválida. Digite um número inteiro."
                );
            }
        }
    }

    private void pausar() {
        System.out.println();
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}
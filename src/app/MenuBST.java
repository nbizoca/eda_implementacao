package app;

import estruturas.bst.BSTImpl;
import estruturas.bst.BSTNode;

import java.util.Arrays;
import java.util.Scanner;

public class MenuBST {

    private final Scanner scanner;

    public MenuBST(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        BSTImpl<Integer> arvore = new BSTImpl<>();
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
                    consultarMinimo(arvore);
                    break;

                case 5:
                    consultarMaximo(arvore);
                    break;

                case 6:
                    exibirPercursos(arvore);
                    break;

                case 7:
                    System.out.println(
                            "\nAltura da árvore: " + arvore.height()
                    );
                    break;

                case 8:
                    System.out.println(
                            "\nQuantidade de nós: " + arvore.size()
                    );
                    break;

                case 9:
                    System.out.println(
                            arvore.isEmpty()
                                    ? "\nA árvore está vazia."
                                    : "\nA árvore não está vazia."
                    );
                    break;

                case 10:
                    demonstrarOperacoes();
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
        System.out.println("Árvore Binária de Busca - BST");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1  - Inserir elemento");
        System.out.println();
        System.out.println("2  - Remover elemento");
        System.out.println();
        System.out.println("3  - Pesquisar elemento");
        System.out.println();
        System.out.println("4  - Consultar valor mínimo");
        System.out.println();
        System.out.println("5  - Consultar valor máximo");
        System.out.println();
        System.out.println("6  - Exibir percursos");
        System.out.println();
        System.out.println("7  - Consultar altura");
        System.out.println();
        System.out.println("8  - Consultar tamanho");
        System.out.println();
        System.out.println("9  - Verificar se está vazia");
        System.out.println();
        System.out.println("10 - Demonstrar operações");
        System.out.println();
        System.out.println("0  - Voltar ao menu principal");
        System.out.println();
    }

    private void inserir(BSTImpl<Integer> arvore) {
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

    private void remover(BSTImpl<Integer> arvore) {
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

    private void pesquisar(BSTImpl<Integer> arvore) {
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

    private void consultarMinimo(BSTImpl<Integer> arvore) {
        BSTNode<Integer> minimo =
                arvore.minimum(arvore.getRoot());

        if (minimo == null) {
            System.out.println("\nA árvore está vazia.");
        } else {
            System.out.println(
                    "\nMenor elemento: " + minimo.getData()
            );
        }
    }

    private void consultarMaximo(BSTImpl<Integer> arvore) {
        BSTNode<Integer> maximo =
                arvore.maximum(arvore.getRoot());

        if (maximo == null) {
            System.out.println("\nA árvore está vazia.");
        } else {
            System.out.println(
                    "\nMaior elemento: " + maximo.getData()
            );
        }
    }

    private void exibirPercursos(BSTImpl<Integer> arvore) {
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

    private void demonstrarOperacoes() {
        BSTImpl<Integer> demonstracao = new BSTImpl<>();

        int[] dados = {40, 20, 60, 10, 30, 50, 70};

        for (int elemento : dados) {
            demonstracao.insert(elemento);
        }

        System.out.println();
        System.out.println(
                "Elementos inseridos: " + Arrays.toString(dados)
        );

        exibirPercursos(demonstracao);

        System.out.println(
                "Altura: " + demonstracao.height()
        );

        System.out.println(
                "Tamanho: " + demonstracao.size()
        );

        System.out.println(
                "Pesquisa pelo 50: "
                        + demonstracao.search(50).getData()
        );

        demonstracao.remove(20);

        System.out.println("Depois de remover 20:");
        exibirPercursos(demonstracao);
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
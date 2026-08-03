package app;

import estruturas.arvoreB.ArvoreBImpl;
import estruturas.arvoreB.BNodePosition;

import java.util.Scanner;

public class MenuArvoreB {

    private final Scanner scanner;

    public MenuArvoreB(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        int ordem = solicitarOrdem();

        ArvoreBImpl<Integer> arvore =
                new ArvoreBImpl<>(ordem);

        int opcao;

        do {
            exibirMenu(ordem);
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    inserir(arvore);
                    break;

                case 2:
                    pesquisar(arvore);
                    break;

                case 3:
                    arvore.printLevels();
                    break;

                case 4:
                    System.out.println(
                            "\nAltura da árvore: " + arvore.height()
                    );
                    break;

                case 5:
                    System.out.println(
                            "\nQuantidade de elementos: "
                                    + arvore.size()
                    );
                    break;

                case 6:
                    demonstrarOrdem3();
                    break;

                case 7:
                    demonstrarOrdem4();
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

    private int solicitarOrdem() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Criação da Árvore B");
        System.out.println("====================================");

        int ordem;

        do {
            ordem = lerInteiro(
                    "Informe a ordem da árvore B: "
            );

            if (ordem < 3) {
                System.out.println(
                        "A ordem deve ser pelo menos 3."
                );
            }

        } while (ordem < 3);

        return ordem;
    }

    private void exibirMenu(int ordem) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Árvore B - Ordem " + ordem);
        System.out.println("====================================");
        System.out.println();
        System.out.println("1 - Inserir elemento");
        System.out.println();
        System.out.println("2 - Pesquisar elemento");
        System.out.println();
        System.out.println("3 - Imprimir níveis");
        System.out.println();
        System.out.println("4 - Consultar altura");
        System.out.println();
        System.out.println("5 - Consultar tamanho");
        System.out.println();
        System.out.println("6 - Demonstrar árvore de ordem 3");
        System.out.println();
        System.out.println("7 - Demonstrar árvore de ordem 4");
        System.out.println();
        System.out.println("0 - Voltar ao menu principal");
        System.out.println();
    }

    private void inserir(ArvoreBImpl<Integer> arvore) {
        int elemento = lerInteiro(
                "Digite o elemento que será inserido: "
        );

        BNodePosition<Integer> resultado =
                arvore.search(elemento);

        if (resultado.getNode() != null) {
            System.out.println(
                    "\nO elemento já existe na árvore."
            );
            return;
        }

        arvore.insert(elemento);

        System.out.println(
                "\nElemento " + elemento + " inserido com sucesso!"
        );

        arvore.printLevels();
    }

    private void pesquisar(ArvoreBImpl<Integer> arvore) {
        int elemento = lerInteiro(
                "Digite o elemento que será pesquisado: "
        );

        BNodePosition<Integer> resultado =
                arvore.search(elemento);

        if (resultado.getNode() == null) {
            System.out.println("\nElemento não encontrado.");
        } else {
            System.out.println("\nElemento encontrado.");
            System.out.println(
                    "Posição dentro do nó: "
                            + resultado.getPosition()
            );
        }
    }

    private void demonstrarOrdem3() {
        ArvoreBImpl<Integer> arvore =
                new ArvoreBImpl<>(3);

        int[] dados = {
                10, 20, 5, 6, 12, 30, 7, 17
        };

        for (int elemento : dados) {
            arvore.insert(elemento);
        }

        System.out.println();
        System.out.println("Árvore B de ordem 3:");
        System.out.println(
                "Elementos: 10, 20, 5, 6, 12, 30, 7 e 17"
        );

        arvore.printLevels();

        System.out.println(
                "Altura: " + arvore.height()
        );

        System.out.println(
                "Quantidade de elementos: " + arvore.size()
        );

        BNodePosition<Integer> pesquisa =
                arvore.search(12);

        System.out.println(
                "Pesquisa pelo elemento 12: "
                        + (pesquisa.getNode() != null
                        ? "encontrado"
                        : "não encontrado")
        );
    }

    private void demonstrarOrdem4() {
        ArvoreBImpl<Integer> arvore =
                new ArvoreBImpl<>(4);

        int[] dados = {
                50, 20, 70, 10, 30, 60,
                80, 5, 15, 25, 35
        };

        for (int elemento : dados) {
            arvore.insert(elemento);
        }

        System.out.println();
        System.out.println("Árvore B de ordem 4:");
        System.out.println(
                "Elementos: 50, 20, 70, 10, 30, 60, "
                        + "80, 5, 15, 25 e 35"
        );

        arvore.printLevels();

        System.out.println(
                "Altura: " + arvore.height()
        );

        System.out.println(
                "Quantidade de elementos: " + arvore.size()
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
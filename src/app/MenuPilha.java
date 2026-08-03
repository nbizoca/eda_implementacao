package app;

import estruturas.pilha.PilhaImpl;

import java.util.Scanner;

public class MenuPilha {

    private final Scanner scanner;

    public MenuPilha(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Criação da Pilha");
        System.out.println("====================================");

        int capacidade;

        do {
            capacidade = lerInteiro(
                    "Informe a capacidade da pilha: "
            );

            if (capacidade <= 0) {
                System.out.println(
                        "A capacidade deve ser maior que zero!"
                );
            }

        } while (capacidade <= 0);

        PilhaImpl<Integer> pilha =
                new PilhaImpl<>(capacidade);

        int opcao;

        do {
            exibirMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    empilhar(pilha);
                    break;

                case 2:
                    desempilhar(pilha);
                    break;

                case 3:
                    consultarTopo(pilha);
                    break;

                case 4:
                    verificarVazia(pilha);
                    break;

                case 5:
                    verificarCheia(pilha);
                    break;

                case 6:
                    demonstrarOperacoes();
                    break;

                case 0:
                    System.out.println();
                    System.out.println(
                            "Voltando ao menu principal..."
                    );
                    break;

                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                pausar();
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Pilha");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1 - Empilhar elemento(Adcionar)");
        System.out.println();
        System.out.println("2 - Desempilhar elemento(Remover)");
        System.out.println();
        System.out.println("3 - Consultar o topo");
        System.out.println();
        System.out.println("4 - Verificar se está vazia");
        System.out.println();
        System.out.println("5 - Verificar se está cheia");
        System.out.println();
        System.out.println("6 - Demonstrar operações");
        System.out.println();
        System.out.println("0 - Voltar ao menu principal");
        System.out.println();
    }

    private void empilhar(PilhaImpl<Integer> pilha) {
        int elemento = lerInteiro(
                "Digite o elemento que será empilhado: "
        );

        try {
            pilha.push(elemento);

            System.out.println();
            System.out.println(
                    "Elemento " + elemento
                            + " empilhado com sucesso!"
            );

            System.out.println(
                    "Topo atual: " + pilha.top()
            );

        } catch (IllegalStateException exception) {
            System.out.println();
            System.out.println(exception.getMessage());
        }
    }

    private void desempilhar(PilhaImpl<Integer> pilha) {
        try {
            Integer elementoRemovido = pilha.pop();

            System.out.println();
            System.out.println(
                    "Elemento removido: "
                            + elementoRemovido
            );

            if (!pilha.isEmpty()) {
                System.out.println(
                        "Novo topo: " + pilha.top()
                );
            } else {
                System.out.println(
                        "A pilha ficou vazia."
                );
            }

        } catch (IllegalStateException exception) {
            System.out.println();
            System.out.println(exception.getMessage());
        }
    }

    private void consultarTopo(PilhaImpl<Integer> pilha) {
        try {
            System.out.println();
            System.out.println(
                    "Elemento no topo: " + pilha.top()
            );

        } catch (IllegalStateException exception) {
            System.out.println();
            System.out.println(exception.getMessage());
        }
    }

    private void verificarVazia(PilhaImpl<Integer> pilha) {
        System.out.println();

        if (pilha.isEmpty()) {
            System.out.println("A pilha está vazia.");
        } else {
            System.out.println("A pilha não está vazia.");
        }
    }

    private void verificarCheia(PilhaImpl<Integer> pilha) {
        System.out.println();

        if (pilha.isFull()) {
            System.out.println("A pilha está cheia.");
        } else {
            System.out.println("A pilha não está cheia.");
        }
    }

    private void demonstrarOperacoes() {
        PilhaImpl<Integer> demonstracao =
                new PilhaImpl<>(5);

        System.out.println();
        System.out.println(
                "Demonstração com os valores 10, 20 e 30:"
        );

        demonstracao.push(10);
        demonstracao.push(20);
        demonstracao.push(30);

        System.out.println(
                "10 foi empilhado."
        );

        System.out.println(
                "20 foi empilhado."
        );

        System.out.println(
                "30 foi empilhado."
        );

        System.out.println();
        System.out.println(
                "Topo atual: " + demonstracao.top()
        );

        Integer removido = demonstracao.pop();

        System.out.println(
                "Elemento desempilhado: " + removido
        );

        System.out.println(
                "Novo topo: " + demonstracao.top()
        );

        System.out.println(
                "A pilha está vazia? "
                        + demonstracao.isEmpty()
        );

        System.out.println(
                "A pilha está cheia? "
                        + demonstracao.isFull()
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
        System.out.println(
                "Pressione Enter para continuar..."
        );

        scanner.nextLine();
    }
}
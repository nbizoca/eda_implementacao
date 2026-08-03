package app;

import estruturas.listaSimples.LinkedListImpl;

import java.util.Arrays;
import java.util.Scanner;

public class MenuListaSimples {

    private final Scanner scanner;

    public MenuListaSimples(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        LinkedListImpl<Integer> lista = new LinkedListImpl<>();
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    inserir(lista);
                    break;

                case 2:
                    remover(lista);
                    break;

                case 3:
                    pesquisar(lista);
                    break;

                case 4:
                    exibirLista(lista);
                    break;

                case 5:
                    System.out.println(
                            "\nQuantidade de elementos: " + lista.size()
                    );
                    break;

                case 6:
                    verificarVazia(lista);
                    break;

                case 7:
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
        System.out.println("Lista Simples");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1 - Inserir elemento");
        System.out.println();
        System.out.println("2 - Remover elemento");
        System.out.println();
        System.out.println("3 - Pesquisar elemento");
        System.out.println();
        System.out.println("4 - Exibir lista");
        System.out.println();
        System.out.println("5 - Consultar tamanho");
        System.out.println();
        System.out.println("6 - Verificar se está vazia");
        System.out.println();
        System.out.println("7 - Demonstrar operações");
        System.out.println();
        System.out.println("0 - Voltar ao menu principal");
        System.out.println();
    }

    private void inserir(LinkedListImpl<Integer> lista) {
        int elemento = lerInteiro(
                "Digite o elemento que será inserido: "
        );

        lista.insert(elemento);

        System.out.println(
                "\nElemento " + elemento + " inserido com sucesso!"
        );

        exibirLista(lista);
    }

    private void remover(LinkedListImpl<Integer> lista) {
        if (lista.isEmpty()) {
            System.out.println("\nA lista está vazia.");
            return;
        }

        int elemento = lerInteiro(
                "Digite o elemento que será removido: "
        );

        Integer encontrado = lista.search(elemento);

        if (encontrado == null) {
            System.out.println(
                    "\nO elemento não existe na lista."
            );
            return;
        }

        lista.remove(elemento);

        System.out.println(
                "\nElemento " + elemento + " removido com sucesso!"
        );

        exibirLista(lista);
    }

    private void pesquisar(LinkedListImpl<Integer> lista) {
        int elemento = lerInteiro(
                "Digite o elemento que será pesquisado: "
        );

        Integer resultado = lista.search(elemento);

        if (resultado == null) {
            System.out.println("\nElemento não encontrado.");
        } else {
            System.out.println(
                    "\nElemento encontrado: " + resultado
            );
        }
    }

    private void exibirLista(LinkedListImpl<Integer> lista) {
        System.out.println(
                "\nLista: " + Arrays.toString(lista.toArray())
        );
    }

    private void verificarVazia(LinkedListImpl<Integer> lista) {
        if (lista.isEmpty()) {
            System.out.println("\nA lista está vazia.");
        } else {
            System.out.println("\nA lista não está vazia.");
        }
    }

    private void demonstrarOperacoes() {
        LinkedListImpl<Integer> demonstracao =
                new LinkedListImpl<>();

        demonstracao.insert(12);
        demonstracao.insert(25);
        demonstracao.insert(38);
        demonstracao.insert(47);

        System.out.println();
        System.out.println("Elementos inseridos: 12, 25, 38 e 47");
        System.out.println(
                "Lista: "
                        + Arrays.toString(demonstracao.toArray())
        );

        System.out.println(
                "Pesquisa pelo 25: "
                        + demonstracao.search(25)
        );

        demonstracao.remove(38);

        System.out.println(
                "Depois de remover 38: "
                        + Arrays.toString(demonstracao.toArray())
        );

        System.out.println(
                "Tamanho da lista: " + demonstracao.size()
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
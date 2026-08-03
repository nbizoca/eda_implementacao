package app;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    MenuPilha menuPilha = new MenuPilha(scanner);
                    menuPilha.iniciar();
                    break;

                case 2:
                    MenuFila menuFila = new MenuFila(scanner);
                    menuFila.iniciar();
                    break;

                case 3:
                    MenuListaSimples menuListaSimples =
                            new MenuListaSimples(scanner);

                    menuListaSimples.iniciar();
                    break;

                case 4:
                    MenuListaDupla menuListaDupla =
                            new MenuListaDupla(scanner);

                    menuListaDupla.iniciar();
                    break;

                case 5:
                    MenuBST menuBST = new MenuBST(scanner);
                    menuBST.iniciar();
                    break;

                case 6:
                    MenuAVL menuAVL = new MenuAVL(scanner);
                    menuAVL.iniciar();
                    break;

                case 7:
                    MenuArvoreB menuArvoreB =
                            new MenuArvoreB(scanner);

                    menuArvoreB.iniciar();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Biblioteca de Estruturas de Dados");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1 - Pilha");
        System.out.println();
        System.out.println("2 - Fila");
        System.out.println();
        System.out.println("3 - Lista Simples");
        System.out.println();
        System.out.println("4 - Lista Dupla");
        System.out.println();
        System.out.println("5 - BST");
        System.out.println();
        System.out.println("6 - AVL");
        System.out.println();
        System.out.println("7 - Árvore B");
        System.out.println();
        System.out.println("0 - Encerrar");
        System.out.println();
    }

    public int lerInteiro(String mensagem) {
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
}
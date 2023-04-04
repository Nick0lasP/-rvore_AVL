package exercicios;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		ArvoreAVL arvore = new ArvoreAVL();
        int escolha = 0;

        while (escolha != 3) {
        	System.out.println("+++++++++++++++++++++ Menu +++++++++++++++++++++\n");
            System.out.println("1 - Inserir um valor na árvore");
            System.out.println("2 - Excluir um valor da árvore");
            System.out.println("3 - Sair do programa");
            System.out.print("\nDigite uma opção: ");
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida. Por favor, selecione uma opção válida.");
                continue;
            }

            switch (escolha) {
                case 1:
                	System.out.print("\nDigite o valor a ser inserido: ");
                	try {
                    int valor = Integer.parseInt(scanner.nextLine());
                    arvore.inserir(valor);
                    arvore.printarArvore();
                  	} catch (NumberFormatException e) {
                        System.out.println("\nValor inválido. Por favor, digite um número inteiro.");
                    } 
                    break;
                case 2:
                    System.out.print("\nDigite o valor a ser excluído: ");
                	try {
                    int valor = Integer.parseInt(scanner.nextLine());
                    arvore.excluir(valor);
                    arvore.printarArvore();
                	} catch (NumberFormatException e) {
                        System.out.println("\nValor inválido. Por favor, digite um número inteiro.");
                    } 
                    break;
                case 3:
                    System.out.println("\nEncerrando...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Escolha um número entre 1 e 3.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}

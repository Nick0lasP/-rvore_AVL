package exercicios;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		ArvoreAVL arvore = new ArvoreAVL();
        int escolha = 0;

        while (escolha != 3) {
        	System.out.println("+++++++++++++++++++++ Menu +++++++++++++++++++++\n");
            System.out.println("1 - Inserir um valor na �rvore");
            System.out.println("2 - Excluir um valor da �rvore");
            System.out.println("3 - Sair do programa");
            System.out.print("\nDigite uma op��o: ");
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nOp��o inv�lida. Por favor, selecione uma op��o v�lida.");
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
                        System.out.println("\nValor inv�lido. Por favor, digite um n�mero inteiro.");
                    } 
                    break;
                case 2:
                    System.out.print("\nDigite o valor a ser exclu�do: ");
                	try {
                    int valor = Integer.parseInt(scanner.nextLine());
                    arvore.excluir(valor);
                    arvore.printarArvore();
                	} catch (NumberFormatException e) {
                        System.out.println("\nValor inv�lido. Por favor, digite um n�mero inteiro.");
                    } 
                    break;
                case 3:
                    System.out.println("\nEncerrando...");
                    break;
                default:
                    System.out.println("\nOp��o inv�lida! Escolha um n�mero entre 1 e 3.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}

package main;

import Structure.Tabela_Hash;
import java.util.Scanner;

import static Cripto.Escrita_e_Leitura.Cripto;


public class Main {
    public static void main(String[] args) {
        Tabela_Hash<String, String> Tabela = new Tabela_Hash<>(10);
        Scanner scanner = new Scanner(System.in);
        Tabela.Inserir_Do_Arquivo("src\\archives\\Entrada1.txt");
        int choice = 0;
        while (choice != 6) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir");
            System.out.println("2. Imprimir");
            System.out.println("3. Buscar");
            System.out.println("4. Remover");
            System.out.println("5. Criptografia MD5");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite a chave: ");
                    String keyInsert = scanner.next();
                    System.out.print("Digite o valor: ");
                    String valueInsert = scanner.next();
                    Tabela.Inserir(keyInsert, valueInsert);
                    System.out.println("Inserido com sucesso!");
                    break;
                case 2:
                    System.out.println("Imprimindo tabela:");
                    Tabela.Imprimir();
                    break;
                case 3:
                    System.out.print("Digite a chave a ser buscada: ");
                    String keySearch = scanner.next();
                    String value = Tabela.Buscar(keySearch);
                    if (value != null) {
                        System.out.println("Valor encontrado: " + value);
                    } else {
                        System.out.println("Chave não encontrada!");
                    }
                    break;
                case 4:
                    System.out.print("Digite a chave a ser removida: ");
                    String keyRemove = scanner.next();
                    Tabela.Remover(keyRemove);
                    System.out.println("Removido com sucesso!");
                    break;
                case 5:
                    if(Cripto("src\\archives\\Entrada1.txt")){
                        System.out.println("Criptografia bem sucedida.");
                    } else {
                        System.out.println("Falha na criptografia.");
                    }
                        break;
                case 6:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
package main;

import Structure.HashTable;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashTable<String, String> table = new HashTable<>(10);
        Scanner scanner = new Scanner(System.in);
        // Insert from file
        table.insertFromFile("src\\archives\\data.txt");

        int choice = 0;
        while (choice != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir");
            System.out.println("2. Imprimir");
            System.out.println("3. Buscar");
            System.out.println("4. Remover");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite a chave: ");
                    String keyInsert = scanner.next();
                    System.out.print("Digite o valor: ");
                    String valueInsert = scanner.next();
                    table.insert(keyInsert, valueInsert);
                    System.out.println("Inserido com sucesso!");
                    break;
                case 2:
                    System.out.println("Imprimindo tabela:");
                    table.print();
                    break;
                case 3:
                    System.out.print("Digite a chave a ser buscada: ");
                    String keySearch = scanner.next();
                    String value = table.search(keySearch);
                    if (value != null) {
                        System.out.println("Valor encontrado: " + value);
                    } else {
                        System.out.println("Chave não encontrada!");
                    }
                    break;
                case 4:
                    System.out.print("Digite a chave a ser removida: ");
                    String keyRemove = scanner.next();
                    table.remove(keyRemove);
                    System.out.println("Removido com sucesso!");
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
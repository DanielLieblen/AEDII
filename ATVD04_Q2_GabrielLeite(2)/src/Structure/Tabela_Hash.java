package Structure;

/*
Autor Daniel Moura
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tabela_Hash<K, V> {
    private Dado_Hash<K, V>[] array;
    private int capacity;
    private int size;

    public Tabela_Hash(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new Dado_Hash[capacity];
    }

    private int Índice(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        return index < 0 ? index + capacity : index; // To ensure index is non-negative
    }

    public void Inserir(K key, V value) {
        int index = Índice(key);
        Dado_Hash<K, V> newNode = new Dado_Hash<>(key, value);

        if (array[index] == null) {
            array[index] = newNode;
        } else {
            Dado_Hash<K, V> current = array[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update value if key already exists
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        // Escrever no arquivo após a inserção
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\archives\\Entrada1.txt", true))) {
            boolean isNewInsertion = true;
            // Verifica se a chave já existe no arquivo
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\archives\\Entrada1.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(key.toString())) {
                        isNewInsertion = false;
                        break;
                    }
                }
            }
            // Se for uma nova inserção, escreve no arquivo
            if (isNewInsertion) {
                writer.println(key.toString() + value.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Remover(K key) {
        int index = Índice(key);
        if (array[index] == null) return;

        Dado_Hash<K, V> current = array[index];
        Dado_Hash<K, V> prev = null;

        while (current != null && !current.key.equals(key)) {
            prev = current;
            current = current.next;
        }

        if (current == null) return;

        if (prev == null) {
            array[index] = current.next;
        } else {
            prev.next = current.next;
        }
        size--;
    }

    public V Buscar(K key) {
        int index = Índice(key);
        Dado_Hash<K, V> current = array[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void Imprimir() {
        for (int i = 0; i < capacity; i++) {
            Dado_Hash<K, V> current = array[i];
            if (current == null) {
                System.out.println("Null");
            } else {
                while (current != null) {
                    System.out.println(current.key.toString() + current.value.toString());
                    current = current.next;
                }
            }
        }
    }

    public void Inserir_Do_Arquivo(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1) { // Verifica se a linha tem pelo menos dois caracteres
                    K key = (K) line.substring(0, 1); // Extrai o primeiro caractere como chave
                    V value = (V) line.substring(1); // Extrai o restante como valor
                    Inserir(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Implementação de uma ordenação, funciona, mas trava quando o espaço do arquivo é nulo (em branco)
    /*public void OrdenarArquivo(String filename) {
        List<String> linhas = new ArrayList<>();

        // Ler o arquivo e armazenar as linhas em uma lista
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Ignorar linhas em branco ou com espaços em branco
                    linhas.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Ordenar as linhas com base no identificador da tabela hash
        Collections.sort(linhas, new Comparator<String>() {
            @Override
            public int compare(String linha1, String linha2) {
                K key1 = (K) linha1.substring(0, 1);
                K key2 = (K) linha2.substring(0, 1);
                int hash1 = Índice(key1);
                int hash2 = Índice(key2);
                if (key1.equals("1")) {
                    return -1; // mover a chave 1 para a primeira posição
                } else if (key2.equals("1")) {
                    return 1; // mover a chave 1 para a primeira posição
                } else {
                    return Integer.compare(hash1, hash2);
                }
            }
        });

        // Gravar as linhas ordenadas de volta no arquivo
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
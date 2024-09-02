package Structure;

import java.io.*;

public class HashTable <K, V>{
    private HashNode<K, V>[] array;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new HashNode[capacity];
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        return index < 0 ? index + capacity : index; // To ensure index is non-negative
    }

    public void insert(K key, V value) {
        int index = getIndex(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (array[index] == null) {
            array[index] = newNode;
        } else {
            HashNode<K, V> current = array[index];
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\archives\\data.txt", true))) {
            boolean isNewInsertion = true;
            // Verifica se a chave já existe no arquivo
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\archives\\data.txt"))) {
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

    public void remove(K key) {
        int index = getIndex(key);
        if (array[index] == null) return;

        HashNode<K, V> current = array[index];
        HashNode<K, V> prev = null;

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

    public V search(K key) {
        int index = getIndex(key);
        HashNode<K, V> current = array[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            HashNode<K, V> current = array[i];
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

//    public void insertFromFile(String filename) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(":");
//                if (parts.length == 2) {
//                    K key = (K) parts[0];
//                    V value = (V) parts[1];
//                    insert(key, value);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void insertFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1) { // Verifica se a linha tem pelo menos dois caracteres
                    K key = (K) line.substring(0, 1); // Extrai o primeiro caractere como chave
                    V value = (V) line.substring(1); // Extrai o restante como valor
                    insert(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

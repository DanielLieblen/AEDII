package util;

import java.util.*;

public class Grafo<T> {
    private Map<T, Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new HashMap<>();
    }

    // Gerenciamento de Vértices
    public void adicionarVertice(T dado) {
        vertices.putIfAbsent(dado, new Vertice<>(dado));
    }

    public void removerVertice(T dado) {
        Vertice<T> vertice = vertices.get(dado);
        if (vertice != null) {
            vertices.values().forEach(v -> v.removerAdjacente(vertice));
            vertices.remove(dado);
        }
    }

    // Gerenciamento de Arestas
    public void adicionarAresta(T origem, T destino) {
        Vertice<T> verticeOrigem = vertices.get(origem);
        Vertice<T> verticeDestino = vertices.get(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            verticeOrigem.adicionarAdjacente(verticeDestino);
            verticeDestino.adicionarAdjacente(verticeOrigem);  // Se for um grafo não direcionado
        }
    }

    public void removerAresta(T origem, T destino) {
        Vertice<T> verticeOrigem = vertices.get(origem);
        Vertice<T> verticeDestino = vertices.get(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            verticeOrigem.removerAdjacente(verticeDestino);
            verticeDestino.removerAdjacente(verticeOrigem);
        }
    }

    // Pesquisas
    public boolean existeVertice(T dado) {
        return vertices.containsKey(dado);
    }

    public boolean existeAresta(T origem, T destino) {
        Vertice<T> verticeOrigem = vertices.get(origem);
        Vertice<T> verticeDestino = vertices.get(destino);
        return verticeOrigem != null && verticeDestino != null && verticeOrigem.getAdjacentes().contains(verticeDestino);
    }

    public List<Vertice<T>> obterAdjacentes(T dado) {
        Vertice<T> vertice = vertices.get(dado);
        return vertice != null ? vertice.getAdjacentes() : null;
    }

    // Impressão do Grafo
    public void imprimirGrafo() {
        vertices.forEach((dado, vertice) -> {
            System.out.print(dado + ": ");
            vertice.getAdjacentes().forEach(adj -> System.out.print(adj.getDado() + " "));
            System.out.println();
        });
    }

    public Vertice<T> obterVertice(T dado) {
        return vertices.get(dado);
    }

    // Método para detectar ciclo
    public boolean detectarCiclo() {
        Map<T, Integer> graus = new HashMap<>();
        Queue<T> fila = new LinkedList<>();

        // Inicializa os graus e coloca na fila os vértices com grau 1
        for (Vertice<T> vertice : vertices.values()) {
            int grau = vertice.getAdjacentes().size();
            graus.put(vertice.getDado(), grau);
            if (grau == 1) {
                fila.offer(vertice.getDado());
            }
        }

        Set<T> visitados = new HashSet<>();

        // Processa a fila
        while (!fila.isEmpty()) {
            T dado = fila.poll();
            visitados.add(dado);

            Vertice<T> vertice = vertices.get(dado);

            for (Vertice<T> adjacente : vertice.getAdjacentes()) {
                T dadoAdjacente = adjacente.getDado();
                if (!visitados.contains(dadoAdjacente)) {
                    int novoGrau = graus.get(dadoAdjacente) - 1;
                    graus.put(dadoAdjacente, novoGrau);
                    if (novoGrau == 1) {
                        fila.offer(dadoAdjacente);
                    }
                }
            }
        }

        // Verifica se há vértices não visitados, o que indicaria um ciclo
        for (Vertice<T> vertice : vertices.values()) {
            if (!visitados.contains(vertice.getDado())) {
                return true;  // Existe um ciclo
            }
        }

        return false;  // Não existe ciclo
    }



    // Método para verificar ciclo usando Ordenação Topológica
    public boolean contemCiclo() {
        Map<Vertice<T>, Integer> grauEntrada = new HashMap<>();
        Queue<Vertice<T>> fila = new LinkedList<>();

        // Inicializa o grau de entrada
        for (Vertice<T> vertice : vertices.values()) {
            grauEntrada.put(vertice, 0);
        }
        for (Vertice<T> vertice : vertices.values()) {
            for (Vertice<T> adjacente : vertice.getAdjacentes()) {
                grauEntrada.put(adjacente, grauEntrada.get(adjacente) + 1);
            }
        }

        // Adiciona os vértices com grau de entrada 0 na fila
        for (Vertice<T> vertice : vertices.values()) {
            if (grauEntrada.get(vertice) == 0) {
                fila.offer(vertice);
            }
        }

        int count = 0;

        // Processa a fila
        while (!fila.isEmpty()) {
            Vertice<T> vertice = fila.poll();
            count++;

            for (Vertice<T> adjacente : vertice.getAdjacentes()) {
                grauEntrada.put(adjacente, grauEntrada.get(adjacente) - 1);
                if (grauEntrada.get(adjacente) == 0) {
                    fila.offer(adjacente);
                }
            }
        }

        // Se o número de vértices processados é igual ao número de vértices no grafo, não há ciclo
        return count != vertices.size();
    }


}

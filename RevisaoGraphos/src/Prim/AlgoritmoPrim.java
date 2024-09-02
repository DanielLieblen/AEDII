package Prim;

import java.util.*;

public class AlgoritmoPrim<T> {

    public void rodar(PrimVertice<T> raiz) {
        PriorityQueue<Map.Entry<PrimVertice<T>, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        Set<PrimVertice<T>> visitados = new HashSet<>();
        List<Map.Entry<PrimVertice<T>, Integer>> arvoreSpanning = new ArrayList<>();

        visitados.add(raiz);

        // Adiciona todas as arestas da raiz na minHeap
        raiz.getVizinhos().entrySet().forEach(minHeap::offer);

        while (!minHeap.isEmpty()) {
            Map.Entry<PrimVertice<T>, Integer> aresta = minHeap.poll();
            PrimVertice<T> destino = aresta.getKey();

            if (!visitados.contains(destino)) {
                visitados.add(destino);
                arvoreSpanning.add(aresta);

                // Adiciona todas as arestas do novo vértice na minHeap
                destino.getVizinhos().entrySet().stream()
                        .filter(entry -> !visitados.contains(entry.getKey()))
                        .forEach(minHeap::offer);
            }
        }

        imprimirArvore(arvoreSpanning);
    }

    private void imprimirArvore(List<Map.Entry<PrimVertice<T>, Integer>> arvoreSpanning) {
        int pesoTotal = arvoreSpanning.stream().mapToInt(Map.Entry::getValue).sum();
        System.out.println("Árvore Geradora Mínima:");
        arvoreSpanning.forEach(aresta -> {
            System.out.println(aresta.getKey() + " com peso " + aresta.getValue());
        });
        System.out.println("Peso total: " + pesoTotal);
    }
}

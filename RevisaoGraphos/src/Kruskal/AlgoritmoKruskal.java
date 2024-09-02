package Kruskal;

import DetectorDeCiclos.DetectorDeCiclos;
import DetectorDeCiclos.VerticeNovo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlgoritmoKruskal<T> {

    private int numeroDeVertices;
    private List<KruskalVertice<T>> vertices;
    private PriorityQueue<ArestaKruskal<T>> grafo;

    public AlgoritmoKruskal(List<ArestaKruskal<T>> grafo) {
        this.grafo = new PriorityQueue<>(grafo);
        vertices = getVerticesNoGrafo(grafo);
        numeroDeVertices = vertices.size();
    }

    private List<KruskalVertice<T>> getVerticesNoGrafo(List<ArestaKruskal<T>> arestas) {
        return Stream.concat(
                arestas.stream().map(ArestaKruskal::getOrigem),
                arestas.stream().map(ArestaKruskal::getDestino)).distinct().collect(Collectors.toList());
    }

    public void rodar() {
        List<ArestaKruskal<T>> arvoreSpanning = new ArrayList<>();

        while (!grafo.isEmpty()) {
            ArestaKruskal<T> aresta = grafo.poll();
            resetarArvore(Stream.concat(arvoreSpanning.stream(), Stream.of(aresta)).collect(Collectors.toList()));

            // Converte KruskalVertice para VerticeNovo
            Map<KruskalVertice<T>, VerticeNovo<T>> mapVertices = vertices.stream()
                    .collect(Collectors.toMap(
                            v -> v,
                            v -> new VerticeNovo<T>(v.getDado())
                    ));

            List<VerticeNovo<T>> verticesConvertidos = vertices.stream()
                    .map(mapVertices::get)
                    .collect(Collectors.toList());

            // Reconfigura as conexões entre os vértices convertidos
            for (KruskalVertice<T> v : vertices) {
                VerticeNovo<T> novoVertice = mapVertices.get(v);
                for (KruskalVertice<T> vizinho : v.getVizinhos()) {
                    novoVertice.getVizinhos().add(mapVertices.get(vizinho));
                }
            }

            if (!new DetectorDeCiclos<T>().temCiclo(verticesConvertidos)) {
                arvoreSpanning.add(aresta);
            }
        }

        imprimirArvoreInfo(arvoreSpanning);
    }

    private void resetarArvore(List<ArestaKruskal<T>> arvoreSpanning) {
        vertices.forEach(vertice -> {
            vertice.setVisitado(false);
            vertice.setVizinhos(new HashSet<>());
        });
        arvoreSpanning.forEach(aresta -> {
            aresta.getOrigem().addVizinho(aresta.getDestino());
            aresta.getDestino().addVizinho(aresta.getOrigem());
        });
    }

    public void imprimirArvoreInfo(List<ArestaKruskal<T>> arvoreSpanning) {
        Integer min = arvoreSpanning.stream()
                .map(ArestaKruskal::getPeso)
                .reduce(0, Integer::sum);
        arvoreSpanning.forEach(System.out::println);
        System.out.println("Peso mínimo: " + min);
    }
}

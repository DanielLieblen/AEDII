package Ford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class FordFulkerson<T> {
    private final ArrayList<List<Vertice<T>>> caminhos = new ArrayList<>();

    public int rodar(Vertice<T> origem, Vertice<T> destino) {
        int fluxoTotal = 0;

        // Encontra todos os caminhos da origem ao destino
        encontrarTodosOsCaminhos(origem, destino, new ArrayList<>(Collections.singleton(origem)));

        // Ordena os caminhos e ajusta o fluxo
        for (List<Vertice<T>> caminho : caminhos.stream().sorted(Comparator.comparingInt(this::getMinFlowNoCaminho).reversed()).toList()) {
            Integer minimo = getMinFlowNoCaminho(caminho);
            fluxoTotal += minimo;
            for (int i = 0; i < caminho.size() - 1; i++) {
                removerMinimoFlowDoCaminho(caminho, minimo, i);
            }
        }

        return fluxoTotal;
    }


    private void removerMinimoFlowDoCaminho(List<Vertice<T>> caminho, Integer minimo, int vertexIdx) {
        caminho.get(vertexIdx).getVizinhos().put(
                caminho.get(vertexIdx + 1),
                getPesoDaArestaDoVizinho(caminho, vertexIdx) - minimo
        );
    }


    private int getMinFlowNoCaminho(List<Vertice<T>> caminho) {
        return IntStream.range(0, caminho.size() - 1)
                .mapToObj(verticeIdx -> getPesoDaArestaDoVizinho(caminho, verticeIdx))
                .min(Integer::compareTo).orElse(0);
    }

    private int getPesoDaArestaDoVizinho(List<Vertice<T>> caminho, int verticeIdx) {
        return caminho.get(verticeIdx).getVizinhos().get(caminho.get(verticeIdx + 1));
    }

    private void encontrarTodosOsCaminhos(Vertice<T> atual, Vertice<T> destino, List<Vertice<T>> caminhoAtual) {
        if (atual.equals(destino)) {
            caminhos.add(new ArrayList<>(caminhoAtual));
            return;
        }

        atual.setVisitado(true);
        atual.getVizinhos().keySet().stream()
                .filter(vizinho -> !vizinho.isVisitado())
                .forEach(vizinho -> {
                    caminhoAtual.add(vizinho);
                    encontrarTodosOsCaminhos(vizinho, destino, caminhoAtual);
                    caminhoAtual.remove(vizinho);
                });
        atual.setVisitado(false);
    }
}
package questao2;

import util.Grafo;
import util.Vertice;

import java.util.*;

public class BuscadorDeCaminho<T> {

    public List<T> buscarCaminho(Grafo<T> grafo, T inicio, T destino) {
        Queue<Vertice<T>> fila = new LinkedList<>();
        Map<T, T> anteriores = new HashMap<>();
        Set<T> visitados = new HashSet<>();

        fila.add(grafo.obterVertice(inicio));
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();
            if (atual.getDado().equals(destino)) {
                List<T> caminho = new LinkedList<>();
                for (T vertice = destino; vertice != null; vertice = anteriores.get(vertice)) {
                    caminho.add(0, vertice);
                }
                return caminho;
            }

            for (Vertice<T> vizinho : atual.getAdjacentes()) {
                if (!visitados.contains(vizinho.getDado())) {
                    fila.add(vizinho);
                    visitados.add(vizinho.getDado());
                    anteriores.put(vizinho.getDado(), atual.getDado());
                }
            }
        }
        return Collections.emptyList();
    }
}

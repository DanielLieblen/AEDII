package questao5;

import util.Grafo;
import util.Vertice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BellmanFord<T> {
    private Grafo<T> grafo;
    private Map<T, Integer> distancia;
    private Map<T, T> antecessor;

    public BellmanFord(Grafo<T> grafo) {
        this.grafo = grafo;
        this.distancia = new HashMap<>();
        this.antecessor = new HashMap<>();
    }

    public List<T> encontrarMenorCaminho(T origem, T destino) {
        inicializar(origem);
        relaxarArestas();
        verificarCiclosNegativos();
        return reconstruirCaminho(destino);
    }

    private void inicializar(T origem) {
        for (Vertice<T> vertice : grafo.getVertices2()) {
            distancia.put(vertice.getDado(), Integer.MAX_VALUE);
            antecessor.put(vertice.getDado(), null);
        }
        distancia.put(origem, 0);
    }

    private void relaxarArestas() {
        for (int i = 0; i < grafo.getVertices().size() - 1; i++) {
            for (Vertice<T> vertice : grafo.getVertices2()) {
                for (Map.Entry<Vertice<T>, Integer> aresta : vertice.getArestas().entrySet()) {
                    T u = vertice.getDado();
                    T v = aresta.getKey().getDado();
                    int peso = aresta.getValue();

                    if (distancia.get(u) != Integer.MAX_VALUE && distancia.get(u) + peso < distancia.get(v)) {
                        distancia.put(v, distancia.get(u) + peso);
                        antecessor.put(v, u);
                    }
                }
            }
        }
    }

    private void verificarCiclosNegativos() {
        for (Vertice<T> vertice : grafo.getVertices2()) {
            for (Map.Entry<Vertice<T>, Integer> aresta : vertice.getArestas().entrySet()) {
                T u = vertice.getDado();
                T v = aresta.getKey().getDado();
                int peso = aresta.getValue();

                if (distancia.get(u) != Integer.MAX_VALUE && distancia.get(u) + peso < distancia.get(v)) {
                    throw new IllegalStateException("O grafo contém um ciclo de peso negativo.");
                }
            }
        }
    }

    private List<T> reconstruirCaminho(T destino) {
        LinkedList<T> caminho = new LinkedList<>();
        for (T vertice = destino; vertice != null; vertice = antecessor.get(vertice)) {
            caminho.addFirst(vertice);
        }
        return caminho;
    }

    public int getDistanciaMenorCaminho(T destino) {
        return distancia.get(destino);
    }
}
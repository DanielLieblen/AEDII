package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertice<T> {
    private T dado;
    private List<Vertice<T>> adjacentes;
    private Map<Vertice<T>, Integer> arestas;

    public Vertice(T dado) {
        this.dado = dado;
        this.adjacentes = new ArrayList<>();
        this.arestas = new HashMap<>();
    }

    public T getDado() {
        return dado;
    }

    public void adicionarAdjacente(Vertice<T> vertice) {
        adjacentes.add(vertice);
    }

    public void removerAdjacente(Vertice<T> vertice) {
        adjacentes.remove(vertice);
    }

    public List<Vertice<T>> getAdjacentes() {
        return adjacentes;
    }

    public void adicionarAresta(Vertice<T> destino, int peso) {
        arestas.put(destino, peso);
    }
    public Map<Vertice<T>, Integer> getArestas() {
        return arestas;
    }

    @Override
    public String toString() {
        return dado.toString();
    }


}

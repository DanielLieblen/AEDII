package util;

import util.Vertice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<T> {
    private final Vertice<T> verticeInicial;

    public BreadthFirstSearch(Vertice<T> verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    public void atravessar() {
        Set<Vertice<T>> visitados = new HashSet<>();
        Queue<Vertice<T>> fila = new LinkedList<>();

        fila.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();
            System.out.println("Visitando vértice: " + atual.getDado());

            for (Vertice<T> vizinho : atual.getAdjacentes()) {
                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    visitados.add(vizinho);
                }
            }
        }
    }
}

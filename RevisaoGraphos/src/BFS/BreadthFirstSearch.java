package BFS;

import util.Vertice;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> {

    private final Vertice<T> verticeInicial;


    public BreadthFirstSearch(Vertice<T> verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    public void atravessar() {
        Queue<Vertice<T>> fila = new LinkedList<>();
        fila.add(verticeInicial);
        verticeInicial.setVisitado(true);  // Marca o vértice inicial como visitado


        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();  // Remove o primeiro vértice da fila
            System.out.println("Visitando vértice: " + atual.getDados());  // Imprime os dados do vértice atual

            // Percorre os vizinhos do vértice atual
            for (Vertice<T> vizinho : atual.getVizinhos()) {
                if (!vizinho.isVisitado()) {
                    vizinho.setVisitado(true);  // Marca como visitado
                    fila.add(vizinho);  // Adiciona à fila
                }
            }


        }


    }
}

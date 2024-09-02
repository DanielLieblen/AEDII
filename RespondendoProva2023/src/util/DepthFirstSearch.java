package util;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch<T> {

    public void atravessar(Vertice<T> verticeInicial) {
        Set<Vertice<T>> visitados = new HashSet<>();
        Stack<Vertice<T>> pilha = new Stack<>();

        pilha.push(verticeInicial);

        while (!pilha.isEmpty()) {
            Vertice<T> atual = pilha.pop();

            if (!visitados.contains(atual)) {
                System.out.println("Visitando vértice: " + atual.getDado());
                visitados.add(atual);

                for (Vertice<T> vizinho : atual.getAdjacentes()) {
                    if (!visitados.contains(vizinho)) {
                        pilha.push(vizinho);
                    }
                }
            }
        }
    }
}

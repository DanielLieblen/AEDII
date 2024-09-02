package DFS;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import util.Vertice;

public class DepthFirstSearch<T> {

    // Método que realiza a busca em profundidade a partir do vértice inicial
    public void atravessar(Vertice<T> verticeInicial) {
        Deque<Vertice<T>> pilha = new LinkedList<>();
        pilha.push((Vertice<T>) verticeInicial);

        while (!pilha.isEmpty()) {
            Vertice<T> atual = pilha.pop(); // Remove o vértice do topo da pilha

            if (!atual.isVisitado()) { // Verifica se o vértice já foi visitado
                atual.setVisitado(true); // Marca o vértice como visitado
                System.out.println("Visitando vértice: " + atual.getDados()); // Imprime os dados do vértice
                Collections.reverse(atual.getVizinhos()); //reverte antes de adicionar a pilha
                // Adiciona os vizinhos do vértice atual à pilha
                for (Vertice<T> vizinho : atual.getVizinhos()) {
                    if (!vizinho.isVisitado()) { // Só adiciona se o vizinho ainda não foi visitado
                        pilha.push(vizinho);
                    }
                }
            }
        }
    }
}

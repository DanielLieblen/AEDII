package util;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private T dado;
    private List<Vertice<T>> adjacentes;

    public Vertice(T dado) {
        this.dado = dado;
        this.adjacentes = new ArrayList<>();
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

    @Override
    public String toString() {
        return dado.toString();
    }


}

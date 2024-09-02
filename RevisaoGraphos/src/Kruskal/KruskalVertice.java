package Kruskal;

import java.util.HashSet;
import java.util.Set;

public class KruskalVertice <T>{

    private T dado;
    private boolean visitado;

    private Set<KruskalVertice<T>> vizinhos = new HashSet<>();

    public KruskalVertice(T dado) {
        this.dado = dado;
        this.visitado = false;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Set<KruskalVertice<T>> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(Set<KruskalVertice<T>> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public void addVizinho(KruskalVertice<T> vizinho) {
        this.vizinhos.add(vizinho);
    }
}

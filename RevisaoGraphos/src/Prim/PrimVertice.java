package Prim;

import java.util.HashMap;
import java.util.Map;

public class PrimVertice<T> {

    private T dado;
    private boolean visitado;
    private Map<PrimVertice<T>, Integer> vizinhos = new HashMap<>();

    public PrimVertice(T dado) {
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

    public Map<PrimVertice<T>, Integer> getVizinhos() {
        return vizinhos;
    }

    public void adicionarVizinho(PrimVertice<T> vizinho, int peso) {
        this.vizinhos.put(vizinho, peso);
    }

    @Override
    public String toString() {
        return dado.toString();
    }
}

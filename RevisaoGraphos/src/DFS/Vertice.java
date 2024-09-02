package DFS;

import java.util.ArrayList;

public class Vertice<T> {

    private T dados;
    private boolean visitado;
    private ArrayList<Vertice<T>> vizinhos = new ArrayList<>();

    public Vertice(T dados){
        this.dados = dados;
        this.visitado = false;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public ArrayList<Vertice<T>> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<Vertice<T>> vizinhos) {
        this.vizinhos = vizinhos;
    }
}

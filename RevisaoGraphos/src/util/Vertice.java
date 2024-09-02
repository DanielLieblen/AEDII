package util;

import java.util.ArrayList;

public class Vertice <T>{

    private T dados;
    private boolean visitados;
    private ArrayList<Vertice<T>> vizinhos = new ArrayList<>();

    public Vertice(T dados){
        this.dados = dados;
        this.visitados = false;

    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public boolean isVisitado() {
        return visitados;
    }

    public void setVisitado(boolean visitados) {
        this.visitados = visitados;
    }

    public ArrayList<Vertice<T>> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<Vertice<T>> vizinhos) {
        this.vizinhos = vizinhos;
    }

}

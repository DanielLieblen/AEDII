package Ford;

import java.util.HashMap;
import java.util.Map;

public class Vertice <T>{
    private T dados;
    private boolean visitado;


    private Map<Vertice<T>, Integer > vizinhos = new HashMap<Vertice<T>, Integer>();
    public Vertice(T data){
        this.dados = data;
        this.visitado = false;
    }

    public T getData() {
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

    public Map<Vertice<T>, Integer> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(Map<Vertice<T>, Integer> vizinhos) {
        this.vizinhos = vizinhos;
    }

    // Método para adicionar um vizinho e a capacidade da aresta
    public void addVizinho(Vertice<T> vizinho, int capacidade) {
        this.vizinhos.put(vizinho, capacidade);
    }
}

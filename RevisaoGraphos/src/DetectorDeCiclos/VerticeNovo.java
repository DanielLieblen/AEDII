package DetectorDeCiclos;

import util.Vertice;

import java.util.HashSet;
import java.util.Set;

public class VerticeNovo<T>{

    private final T dado;
    private boolean visitado;
    private boolean sendoVisitado;


    private Set<VerticeNovo<T>> vizinhos = new HashSet<>();


    public VerticeNovo(T dado){
        this.dado = dado;
    }

    public void adicionar(VerticeNovo<T> vizinho){
        vizinhos.add(vizinho);
    }

    public T getDado() {
        return dado;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public boolean isSendoVisitado() {
        return sendoVisitado;
    }

    public void setSendoVisitado(boolean sendoVisitado) {
        this.sendoVisitado = sendoVisitado;
    }

    public Set<VerticeNovo<T>> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(Set<VerticeNovo<T>> vizinhos) {
        this.vizinhos = vizinhos;
    }
}

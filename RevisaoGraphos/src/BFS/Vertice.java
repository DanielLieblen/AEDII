package BFS;

public class Vertice <T>{
    private T elemento;
    private boolean visitado;
    public Vertice(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }
    public boolean isVisitado() {
        return visitado;
    }
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

}

package Kruskal;

public class ArestaKruskal<T> implements Comparable<ArestaKruskal<T>> {

    private KruskalVertice<T> origem;
    private KruskalVertice<T> destino;
    private int peso;

    @Override
    public int compareTo(ArestaKruskal<T> aresta) {
        return Integer.compare(this.peso, aresta.getPeso()); // Para a árvore geradora mínima
        // Para uma árvore geradora máxima, inverta a ordem: return Integer.compare(aresta.getPeso(), this.peso);
    }

    public KruskalVertice<T> getOrigem() {
        return origem;
    }

    public void setOrigem(KruskalVertice<T> origem) {
        this.origem = origem;
    }

    public KruskalVertice<T> getDestino() {
        return destino;
    }

    public void setDestino(KruskalVertice<T> destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}

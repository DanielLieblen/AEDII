package questao5;

import util.Grafo;

public class Mapa {
    private Grafo<String> grafo;
    private String origem;
    private String destino;

    public Mapa(Grafo<String> grafo, String origem, String destino) {
        this.grafo = grafo;
        this.origem = origem;
        this.destino = destino;
    }

    public Grafo<String> getGrafo() {
        return grafo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }
}
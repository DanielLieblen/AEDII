package questao2;

import util.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labirinto {
    private Grafo<Integer> grafo;
    private int linhas;
    private int colunas;
    private int entrada;
    private int saida;

    public Labirinto(String caminhoArquivo) throws IOException {
        this.grafo = new Grafo<>();
        carregarLabirinto(caminhoArquivo);
    }

    private void carregarLabirinto(String caminhoArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int contador = 0;
        int linhaAtual = 0;
        while ((linha = br.readLine()) != null) {
            if (colunas == 0) {
                colunas = linha.length();
            }
            for (int i = 0; i < linha.length(); i++) {
                char c = linha.charAt(i);
                if (c != 'X') {
                    grafo.adicionarVertice(contador);
                    System.out.println("Vertice adicionado: " + contador); // Debug: Imprime cada vértice adicionado
                    if (c == 'E') {
                        entrada = contador;
                    } else if (c == 'S') {
                        saida = contador;
                    }

                    if (linhaAtual > 0 && grafo.existeVertice(contador - colunas)) {
                        grafo.adicionarAresta(contador, contador - colunas);
                        System.out.println("Aresta adicionada entre " + contador + " e " + (contador - colunas)); // Debug
                    }
                    if (i > 0 && grafo.existeVertice(contador - 1)) {
                        grafo.adicionarAresta(contador, contador - 1);
                        System.out.println("Aresta adicionada entre " + contador + " e " + (contador - 1)); // Debug
                    }
                }
                contador++;
            }
            linhaAtual++;
        }
        br.close();
        linhas = linhaAtual;
    }


    public Grafo<Integer> getGrafo() {
        return grafo;
    }

    public int getEntrada() {
        return entrada;
    }

    public int getSaida() {
        return saida;
    }
}

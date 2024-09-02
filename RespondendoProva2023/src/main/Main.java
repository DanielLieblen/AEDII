package main;

import archive.LeitorDeArquivoGrafo;
import util.BreadthFirstSearch;
import util.DepthFirstSearch;
import util.Grafo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Especifique o caminho do arquivo
        String caminhoArquivo = "src/data/Arquivo.txt";

        try {
            // Carrega o grafo a partir do arquivo
            Grafo<String> grafo = LeitorDeArquivoGrafo.carregarGrafo(caminhoArquivo);

            // Imprime o grafo carregado
            System.out.println("Grafo carregado:");
            grafo.imprimirGrafo();

            // Teste do BFS e DFS
            testarBfsEDfs(grafo);

            // Outras operações que você deseja realizar no grafo
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    //Q2
        Grafo<String> grafo = LeitorDeArquivoGrafo.carregarGrafo(caminhoArquivo);
        testarDeteccaoDeCiclos(grafo);

    }

    private static void testarBfsEDfs(Grafo<String> grafo) {
        String verticeInicial = "A"; // Defina o vértice inicial para os testes

        if (grafo.existeVertice(verticeInicial)) {
            BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(grafo.obterVertice(verticeInicial));
            bfs.atravessar();
            System.out.println();

            DepthFirstSearch<String> dfs = new DepthFirstSearch<>();
            dfs.atravessar(grafo.obterVertice(verticeInicial));
        } else {
            System.out.println("O vértice " + verticeInicial + " não existe no grafo.");
        }
    }
//Q2
    public static void testarDeteccaoDeCiclos(Grafo<String> grafo) {
        if (grafo.detectarCiclo()) {
            System.out.println("O grafo contém ciclos.");
        } else {
            System.out.println("O grafo não contém ciclos.");
        }
    }


//Q3
public static void testarCicloTopologico(Grafo<String> grafo) {
    if (grafo.contemCiclo()) {
        System.out.println("O grafo contém ciclos.");
    } else {
        System.out.println("O grafo não contém ciclos.");
    }
}



}

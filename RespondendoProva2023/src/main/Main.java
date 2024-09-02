package main;

import archive.LeitorDeArquivoGrafo;
import questao2.BuscadorDeCaminho;
import questao2.Labirinto;
import questao5.BellmanFord;
import questao5.Mapa;
import util.BreadthFirstSearch;
import util.DepthFirstSearch;
import util.Grafo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Especifique o caminho do arquivo
        String caminhoArquivo = "src/data/Arquivo.txt";
        String caminhoArquivoLabirinto = "src/data/Labirinto.txt";
        String caminhoArquivoMapa = "src/data/Mapa.txt"; // Arquivo para a quinta questão

        try {
            // Carrega o grafo a partir do arquivo
            Grafo<String> grafo = LeitorDeArquivoGrafo.carregarGrafo(caminhoArquivo);

            // Imprime o grafo carregado
            System.out.println("Grafo carregado:");
            grafo.imprimirGrafo();

            // Teste do BFS e DFS (Questão 1)
            testarBfsEDfs(grafo);

            // Teste de detecção de ciclos com a abordagem da fila dinâmica (Questão 2)
            testarDeteccaoDeCiclos(grafo);

            // Teste de detecção de ciclos com ordenação topológica (Questão 3)
            testarCicloTopologico(grafo);

            // Teste do labirinto (Questão 4)
            testarLabirinto(caminhoArquivoLabirinto);

            // Teste do algoritmo de Bellman-Ford para encontrar a menor rota (Questão 5)
            testarMenorRota(caminhoArquivoMapa);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void testarLabirinto(String caminhoArquivoLabirinto) {
        try {
            // Carrega o labirinto e o grafo correspondente
            Labirinto labirinto = new Labirinto(caminhoArquivoLabirinto);
            Grafo<Integer> grafo = labirinto.getGrafo();

            // Busca o caminho da entrada para a saída
            BuscadorDeCaminho<Integer> buscador = new BuscadorDeCaminho<>();
            List<Integer> caminho = buscador.buscarCaminho(grafo, labirinto.getEntrada(), labirinto.getSaida());

            // Imprime o caminho encontrado
            if (!caminho.isEmpty()) {
                System.out.println("Caminho encontrado: " + caminho);
            } else {
                System.out.println("Não foi possível encontrar um caminho.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do labirinto: " + e.getMessage());
        }
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

    // Questão 2: Detecção de ciclos com a abordagem da fila dinâmica
    private static void testarDeteccaoDeCiclos(Grafo<String> grafo) {
        if (grafo.detectarCiclo()) {
            System.out.println("O grafo contém ciclos.");
        } else {
            System.out.println("O grafo não contém ciclos.");
        }
    }

    // Questão 3: Detecção de ciclos usando ordenação topológica
    private static void testarCicloTopologico(Grafo<String> grafo) {
        if (grafo.contemCiclo()) {
            System.out.println("O grafo contém ciclo topológico.");
        } else {
            System.out.println("O grafo não contém ciclo topológico.");
        }
    }

    // Questão 5: Encontrar a menor rota usando o algoritmo de Bellman-Ford
    private static void testarMenorRota(String caminhoArquivoMapa) {
        try {
            // Carrega o mapa a partir do arquivo
            Mapa mapa = LeitorDeArquivoGrafo.carregarMapa(caminhoArquivoMapa);

            // Executa o algoritmo de Bellman-Ford para encontrar o menor caminho
            BellmanFord<String> bellmanFord = new BellmanFord<>(mapa.getGrafo());
            List<String> caminho = bellmanFord.encontrarMenorCaminho(mapa.getOrigem(), mapa.getDestino());
            int distancia = bellmanFord.getDistanciaMenorCaminho(mapa.getDestino());

            // Exibe o resultado
            System.out.println("Menor distância de " + mapa.getOrigem() + " para " + mapa.getDestino() + " é: " + distancia);
            System.out.println("Caminho: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do mapa: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erro ao processar o grafo: " + e.getMessage());
        }
    }
}

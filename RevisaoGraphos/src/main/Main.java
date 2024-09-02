package main;

import BFS.BreadthFirstSearch;
import Dijkstra.Dijkstra;
import Ford.FordFulkerson;
import Kruskal.AlgoritmoKruskal;
import Kruskal.ArestaKruskal;
import Kruskal.KruskalVertice;
import util.No;
import DFS.DepthFirstSearch;
import Ford.Vertice;
import DetectorDeCiclos.DetectorDeCiclos;
import DetectorDeCiclos.VerticeNovo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Teste do BFS e DFS
        testarBfsEDfs();

        // Teste do Dijkstra
        testarDijkstra();

        // Teste do Ford-Fulkerson
        testarFordFulkerson();

        // Teste do Detector de Ciclos
        testarDetectorDeCiclos();

        // Teste do Algoritmo de Kruskal
        testarKruskal();
    }

    private static void testarBfsEDfs() {
        util.Vertice<Integer> v0 = new util.Vertice<>(0);
        util.Vertice<Integer> v1 = new util.Vertice<>(1);
        util.Vertice<Integer> v2 = new util.Vertice<>(2);
        util.Vertice<Integer> v3 = new util.Vertice<>(3);
        util.Vertice<Integer> v4 = new util.Vertice<>(4);
        util.Vertice<Integer> v5 = new util.Vertice<>(5);
        util.Vertice<Integer> v6 = new util.Vertice<>(6);

        v0.setVizinhos(new ArrayList<>(Arrays.asList(v1, v5, v6)));
        v1.setVizinhos(new ArrayList<>(Arrays.asList(v3, v4, v5)));
        v4.setVizinhos(new ArrayList<>(Arrays.asList(v2, v6)));
        v6.setVizinhos(new ArrayList<>(Arrays.asList(v0)));

        System.out.println("BFS: ");
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(v0);
        bfs.atravessar();

        // Reseta o status de visitado dos vértices
        resetVisitados(v0, v1, v2, v3, v4, v5, v6);

        System.out.println("DFS: ");
        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.atravessar(v0);
    }

    public static void resetVisitados(util.Vertice<?>... vertices) {
        for (util.Vertice<?> vertice : vertices) {
            vertice.setVisitado(false);
        }
    }

    private static void testarDijkstra() {
        No noA = new No("A");
        No noB = new No("B");
        No noC = new No("C");
        No noD = new No("D");
        No noE = new No("E");
        No noF = new No("F");

        noA.adicionarDestino(noB, 2);
        noA.adicionarDestino(noC, 4);

        noB.adicionarDestino(noC, 3);
        noB.adicionarDestino(noD, 1);
        noB.adicionarDestino(noE, 5);

        noC.adicionarDestino(noD, 2);

        noD.adicionarDestino(noE, 1);
        noD.adicionarDestino(noF, 4);

        noF.adicionarDestino(noF, 2);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.calcularMenorCaminho(noA);

        System.out.println("Menor distância de A para F: " + noF.getDistancia());
        imprimirCaminhos(Arrays.asList(noA, noB, noC, noD, noE, noF));
    }

    private static void imprimirCaminhos(List<No> nos) {
        nos.forEach(no -> {
            String caminho = no.getMenorCaminho().stream()
                    .map(No::getNome)
                    .collect(Collectors.joining(" -> "));
            System.out.println(caminho.isBlank()
                    ? "%s : %s".formatted(no.getNome(), no.getDistancia())
                    : "%s -> %s : %s".formatted(caminho, no.getNome(), no.getDistancia()));
        });
    }

    private static void testarFordFulkerson() {
        Vertice<String> s = new Vertice<>("S"); // Fonte
        Vertice<String> t = new Vertice<>("T"); // Sumidouro
        Vertice<String> a = new Vertice<>("A");
        Vertice<String> b = new Vertice<>("B");
        Vertice<String> c = new Vertice<>("C");
        Vertice<String> d = new Vertice<>("D");

        // Definindo as capacidades das arestas
        s.addVizinho(a, 10);
        s.addVizinho(b, 5);
        s.addVizinho(c, 15);

        a.addVizinho(b, 4);
        a.addVizinho(d, 9);
        a.addVizinho(t, 10);

        b.addVizinho(c, 4);
        b.addVizinho(d, 8);

        c.addVizinho(t, 10);

        d.addVizinho(t, 10);

        FordFulkerson<String> fordFulkerson = new FordFulkerson<>();
        int fluxoMaximo = fordFulkerson.rodar(s, t);

        System.out.println("O fluxo máximo da fonte S para o sumidouro T é: " + fluxoMaximo);
    }

    private static void testarDetectorDeCiclos() {
        // Criando vértices
        VerticeNovo<String> verticeA = new VerticeNovo<>("A");
        VerticeNovo<String> verticeB = new VerticeNovo<>("B");
        VerticeNovo<String> verticeC = new VerticeNovo<>("C");
        VerticeNovo<String> verticeD = new VerticeNovo<>("D");
        VerticeNovo<String> verticeE = new VerticeNovo<>("E");

        // Definindo as arestas
        verticeA.adicionar(verticeB);
        verticeB.adicionar(verticeC);
        verticeC.adicionar(verticeD);
        verticeD.adicionar(verticeE);

        // Criando um ciclo
        verticeE.adicionar(verticeB); // Isso cria um ciclo: E -> B -> C -> D -> E

        // Adicionando todos os vértices em uma lista
        List<VerticeNovo<String>> vertices = Arrays.asList(verticeA, verticeB, verticeC, verticeD, verticeE);

        // Instanciando o detector de ciclos
        DetectorDeCiclos<String> detector = new DetectorDeCiclos<String>();

        // Verificando se há ciclo
        boolean haCiclo = detector.temCiclo(vertices);

        // Exibindo o resultado
        if (haCiclo) {
            System.out.println("O grafo contém um ciclo.");
        } else {
            System.out.println("O grafo não contém um ciclo.");
        }
    }

    private static void testarKruskal() {
        // Criando os vértices
        KruskalVertice<String> verticeA = new KruskalVertice<>("A");
        KruskalVertice<String> verticeB = new KruskalVertice<>("B");
        KruskalVertice<String> verticeC = new KruskalVertice<>("C");
        KruskalVertice<String> verticeD = new KruskalVertice<>("D");
        KruskalVertice<String> verticeE = new KruskalVertice<>("E");

        // Criando as arestas com seus respectivos pesos
        ArestaKruskal<String> arestaAB = new ArestaKruskal<>();
        arestaAB.setOrigem(verticeA);
        arestaAB.setDestino(verticeB);
        arestaAB.setPeso(1);

        ArestaKruskal<String> arestaBC = new ArestaKruskal<>();
        arestaBC.setOrigem(verticeB);
        arestaBC.setDestino(verticeC);
        arestaBC.setPeso(4);

        ArestaKruskal<String> arestaCD = new ArestaKruskal<>();
        arestaCD.setOrigem(verticeC);
        arestaCD.setDestino(verticeD);
        arestaCD.setPeso(5);

        ArestaKruskal<String> arestaDE = new ArestaKruskal<>();
        arestaDE.setOrigem(verticeD);
        arestaDE.setDestino(verticeE);
        arestaDE.setPeso(2);

        ArestaKruskal<String> arestaAE = new ArestaKruskal<>();
        arestaAE.setOrigem(verticeA);
        arestaAE.setDestino(verticeE);
        arestaAE.setPeso(3);

        // Lista de arestas do grafo
        List<ArestaKruskal<String>> arestas = Arrays.asList(arestaAB, arestaBC, arestaCD, arestaDE, arestaAE);

        // Executando o algoritmo de Kruskal
        AlgoritmoKruskal<String> kruskal = new AlgoritmoKruskal<>(arestas);
        kruskal.rodar();

        // Imprimindo a árvore geradora mínima e seu peso total
        System.out.println("Árvore Geradora Mínima calculada pelo Algoritmo de Kruskal:");
        kruskal.imprimirArvoreInfo(arestas);
    }
}

package Dijkstra;

import util.No;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstra {


        public void calcularMenorCaminho(No raiz){
            raiz.setDistancia(0);
            Set<No> nosConfirmados = new HashSet<>();
            Queue<No> nosNaoConfirmados = new PriorityQueue<>(Collections.singleton(raiz));
            nosNaoConfirmados.add(raiz);

            while (!nosNaoConfirmados.isEmpty()){
                No noAtual = nosNaoConfirmados.poll();
                nosConfirmados.add(noAtual);

                noAtual.getNosAdjacentes()
                        .entrySet().stream()
                        .filter(entry -> !nosConfirmados.contains(entry.getKey()))
                        .forEach(entry ->{
                            avaliarDistanciaECaminho(entry.getKey(), entry.getValue(), noAtual);
                            nosNaoConfirmados.add(entry.getKey());
                        });
                nosConfirmados.add(noAtual);
            }

        }

    private void avaliarDistanciaECaminho(No noAdjacente, Integer pesoAresta, No noRaiz) {
        Integer novaDistancia = noRaiz.getDistancia() + pesoAresta;
        if (novaDistancia < noAdjacente.getDistancia()) {
            noAdjacente.setDistancia(novaDistancia);

            // Criando um novo caminho baseado no caminho atual do nó raiz e adicionando o nó raiz ao final
            LinkedList<No> novoCaminho = new LinkedList<>(noRaiz.getMenorCaminho());
            novoCaminho.add(noRaiz);
            noAdjacente.setMenorCaminho(novoCaminho);
        }
    }


}

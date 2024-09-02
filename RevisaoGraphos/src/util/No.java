package util;


import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class No implements Comparable<No>{

   private final String nome;
   private Integer distancia = Integer.MAX_VALUE;  // Inicialmente, a distância é "infinita"
   private Map<No, Integer> nosAdjacentes = new HashMap<>();
   @Setter
   private LinkedList<No> menorCaminho = new LinkedList<>();

    public No(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Map<No, Integer> getNosAdjacentes() {
        return nosAdjacentes;
    }

    public void adicionarDestino(No destino, int distancia) {
        nosAdjacentes.put(destino, distancia);
    }

    public LinkedList<No> getMenorCaminho() {
        return menorCaminho;
    }

    public void setMenorCaminho(LinkedList<No> menorCaminho) {
        this.menorCaminho = menorCaminho;
    }

    @Override
    public int compareTo(No outroNo) {
        return this.distancia.compareTo(outroNo.getDistancia());
    }

    @Override
    public String toString() {
        return nome;
    }
}

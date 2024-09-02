package Cripto;

/*
Autor Gabriel Leite
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Escrita_e_Leitura {
    public static String[] Leitor(String Caminho) {
        try {
            FileReader Arquivo = new FileReader(Caminho);
            BufferedReader LerArquivo = new BufferedReader(Arquivo);
            List<String> linhas = new ArrayList<>(); // Usando uma lista para armazenar as linhas

            String linha = LerArquivo.readLine();
            while (linha != null) {
                linhas.add(linha); // Adicionando cada linha à lista
                linha = LerArquivo.readLine();
            }

            Arquivo.close();
            return linhas.toArray(new String[0]); // Convertendo a lista em um array de strings

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado");
            return new String[0]; // Retornando um array vazio
        } catch (IOException ex) {
            System.out.println("Erro: Não foi possível ler o arquivo!");
            return new String[0]; // Retornando um array vazio
        }
    }

    public static boolean Cripto(String Origem) {
        String[] Dados = Leitor(Origem);
        String Caminho = "src\\archives\\Entrada2.txt";
        if (Dados.length > 0) {
            try (FileWriter Escrito = new FileWriter(Caminho)) {
                for (String linha : Dados) {
                    if (linha != null && !linha.isEmpty()) {
                        String hash = MD5.Texto_Hexadecimal(MD5.CriptoMD5(linha.getBytes()));
                        Escrito.write(hash + "\n"); // Grava a linha criptografada no arquivo
                    } else {
                        Escrito.write("null\n"); // Grava "null" para linhas nulas ou em branco
                    }
                }
                Escrito.close();
                return true; // Retorna true para indicar que a função foi bem sucedida
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível escrever no arquivo!");
                return false;
            }
        }
        return false;
    }

   /* public static void main(String[] args) {
        //GRAVAÇÃO
       String texto = "Lorem Ipslom KKKKKKKK\r\nKKKKKKKKKKKKK";
        if(Escritor(arq,texto)){
            System.out.println("Escrito");
        } else {
            System.out.println("Recusado");
        }

        //LEITURA
        String[] texto = Arquivo.Leitor(arq);
        if(texto == null){
            System.out.println("Erro ao ler o arquivo");
        } else {
            System.out.println(texto);
        }

        Cripto("src\\archives\\Entrada1.txt");
    }*/
    }
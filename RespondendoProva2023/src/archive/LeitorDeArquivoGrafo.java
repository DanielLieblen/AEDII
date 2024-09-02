package archive;

import util.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivoGrafo {

    public static Grafo<String> carregarGrafo(String caminhoArquivo) throws IOException {
        Grafo<String> grafo = new Grafo<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] vertices = linha.split(";");
                if (vertices.length == 2) {
                    String vertice1 = vertices[0].trim();
                    String vertice2 = vertices[1].trim();

                    // Adicionando os vértices ao grafo
                    grafo.adicionarVertice(vertice1);
                    grafo.adicionarVertice(vertice2);

                    // Adicionando a aresta
                    if (!grafo.existeAresta(vertice2, vertice1)) {  // Evita duplicação bidirecional
                        grafo.adicionarAresta(vertice1, vertice2);
                    }
                }
            }
        }

        return grafo;
    }
}

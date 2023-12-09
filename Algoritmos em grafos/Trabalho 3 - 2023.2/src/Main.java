import entity.Vertex;
import util.File;
import util.Util;
import util.flowInNetworks.FordFulkerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Vertex> graph = new ArrayList<>();

        File.readFile(graph, "C:/Users/soare/OneDrive/Área de Trabalho/grafo.txt");

        System.out.println("Digite o vertice fonte e sorvedouro. ex: '0 5'");
        String[] vertices = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        Vertex u = Util.getVertex(graph, vertices[0]);
        Vertex v = Util.getVertex(graph, vertices[1]);

        FordFulkerson.fordFulkerson(graph, graph.get(graph.indexOf(u)), graph.get(graph.indexOf(v)));

        Util.print(u, v);

        File.writeFile(graph, "C:/Users/soare/OneDrive/Área de Trabalho/fluxo maximo.txt");

        bufferedReader.close();
    }
}
import entity.Vertex;
import util.File;
import util.Util;
import util.dag.DAG;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Vertex> graph = new ArrayList<>();

        File.readFile(graph, "C:/Users/soare/OneDrive/Área de Trabalho/grafo.txt");

        System.out.println("Digite o vertice inicial e final. ex: '0 5'");
        String[] vertices = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        Vertex v = Util.getVertex(graph, vertices[0]);
        Vertex u = Util.getVertex(graph, vertices[1]);

        DAG.dagShortestPaths(graph, graph.get(graph.indexOf(v)));

        Util.print(v, u);

        File.writeFile(graph, "C:/Users/soare/OneDrive/Área de Trabalho/caminhos minimos.txt");

        bufferedReader.close();
    }
}
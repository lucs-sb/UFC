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

        File.readFile(graph, "C:/Users/joaom/Desktop/grafo.txt");

        Vertex v, u;

        while(true) {
            System.out.println("Digite o vertice inicial e final. ex: '0 5'");
            String[] vertices = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            if(vertices.length < 2) {
                System.out.println("Digite novamente");
                continue;
            }

            v = Util.getVertex(graph, vertices[0]);
            u = Util.getVertex(graph, vertices[1]);

            if(v == null || u == null) {
                System.out.println("Vertice invalido, digite novamente");
            }else {
                break;
            }
        }

        DAG.dagShortestPaths(graph, graph.get(graph.indexOf(v)));

        Util.print(v, u);

        File.writeFile(graph, "C:/Users/joaom/Desktop/caminhos minimos.txt");

        bufferedReader.close();
    }
}
package util;

import entity.Vertex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File {
    public static void readFile(List<Vertex> graph, String filename) throws IOException {
        Path file = Paths.get(filename);
        List<String> fileLines = Files.lines(file).toList();

        for (int i = 2; i <= Integer.parseInt(fileLines.get(0)); i++){
            String[] vector = fileLines.get(i).split(" ");
            Vertex v = Util.getVertex(graph, vector[0]);
            Vertex u = Util.getVertex(graph, vector[1]);

            if (v == null) {
                v = new Vertex(vector[0]);
                graph.add(v);
            }

            if (u == null) {
                u = new Vertex(vector[1]);
                graph.add(u);
            }

            v.adj.add(u);
            v.adjValues.put(u, Float.parseFloat(vector[2]));
        }
    }

    public static void writeFile(List<Vertex> graph, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        List<String> path = new ArrayList<>();

        for (Vertex x : graph) {
            path.clear();
            if (x.ancestor != null){
                Vertex ancestor = x.ancestor;
                path.add(0, ancestor.name);
                while (ancestor.ancestor != null){
                    ancestor = ancestor.ancestor;
                    path.add(0, ancestor.name);
                }
                for (String s : path)
                    printWriter.print(s + " -> ");
                printWriter.print(x.name + x.d + "\n");
            }
        }

        fileWriter.close();
    }
}

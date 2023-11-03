package util.dfs;

import entity.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static List<Vertex> topologicalSort(List<Vertex> graph){
        List<Vertex> newGraph = new ArrayList<>();

        for (Vertex u : graph)
            if (!u.visit)
                dfsVisit(newGraph, u);

        return newGraph;
    }

    private static void dfsVisit(List<Vertex> newGraph, Vertex u){
        u.visit = true;

        for (Vertex v : u.adj)
            if (!v.visit)
                dfsVisit(newGraph, v);

        newGraph.add(0, u);
    }
}

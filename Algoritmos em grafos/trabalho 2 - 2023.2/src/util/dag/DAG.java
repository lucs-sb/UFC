package util.dag;

import entity.Vertex;
import util.dfs.DFS;

import java.util.List;

public class DAG {
    public static void dagShortestPaths(List<Vertex> graph, Vertex s){
        graph = DFS.topologicalSort(graph);

        initializeSingleSource(graph, s);

        for (Vertex u : graph)
            for (Vertex v : u.adj)
                relax(u, v, u.adjValues.get(v));
    }

    private static void relax(Vertex u, Vertex v, float w){
        if (v.d > u.d + w) {
            v.d = u.d + w;
            v.ancestor = u;
        }
    }

    private static void initializeSingleSource(List<Vertex> graph, Vertex s){
        for (Vertex v : graph){
            v.d = Integer.MAX_VALUE;
            v.ancestor = null;
        }

        s.d = 0;
    }
}

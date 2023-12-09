package util.dfs;

import entity.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static void dfs(Vertex u){
        dfsVisit(u);
    }

    private static void dfsVisit(Vertex u){
        u.visit = true;

        for (Vertex x : u.adj)
            if (!x.visit) {
                x.ancestor = u;
                dfsVisit(x);
            }
    }
}

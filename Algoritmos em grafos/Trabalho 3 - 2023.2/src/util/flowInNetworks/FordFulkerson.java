package util.flowInNetworks;

import entity.Vertex;
import util.Util;
import util.dfs.DFS;

import java.util.List;

public class FordFulkerson {
    public static void fordFulkerson(List<Vertex> graph, Vertex source, Vertex drain){
        Vertex sourcef = null;
        Vertex drainf = null;

        for (Vertex u : graph)
            for (Vertex v : u.adj)
                u.adjFlow.put(v, 0F);

        List<Vertex> residual = Util.buildResidual(graph);

        for (Vertex u : residual){
            if (source.name.equals(u.name))
                sourcef = u;
            if (drain.name.equals(u.name))
                drainf = u;
        }

        DFS.dfs(sourcef);

        while (drainf.ancestor != null){
            Vertex uf = drainf.ancestor;
            Vertex vf = drainf;
            Vertex u = null;
            Vertex v = null;
            float minCapacityf = Util.getMinCapacityPath(drainf);
            while (uf != null){
                boolean isPresent = false;

                for (Vertex z : graph) {
                    if (z.name.equals(uf.name)) {
                        u = z;
                        for (Vertex x : z.adj)
                            if (x.name.equals(vf.name)) {
                                isPresent = true;
                                break;
                            }
                    }
                    if (z.name.equals(vf.name))
                        v = z;
                }

                if (isPresent) {
                    u.adjFlow.put(v, u.adjFlow.get(v) + minCapacityf);
                }
                else
                    v.adjFlow.put(u, v.adjFlow.get(u) - minCapacityf);

                vf = uf;
                uf = uf.ancestor;
            }

            residual = Util.buildResidual(graph);

            for (Vertex x : residual){
                if (source.name.equals(x.name))
                    sourcef = x;
                if (drain.name.equals(x.name))
                    drainf = x;
            }

            DFS.dfs(sourcef);
        }
    }
}
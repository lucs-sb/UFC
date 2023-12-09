package util;

import entity.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Vertex getVertex(List<Vertex> graph, String name){
        for (Vertex v : graph)
            if (name.equals(v.name))
                return v;

        return null;
    }

    public static List<Vertex> buildResidual(List<Vertex> graph){
        List<Vertex> residual = new ArrayList<>();

        for (Vertex x : graph){
            Vertex u = getVertex(residual, x.name);
            if (u == null){
                u = new Vertex(x.name);
                residual.add(u);
            }

            for (Vertex y : x.adj){
                Vertex v = getVertex(residual, y.name);
                if (v == null){
                    v = new Vertex(y.name);
                    residual.add(v);
                }

                float cf = x.adjCapacity.get(y) - x.adjFlow.get(y);
                float f = x.adjFlow.get(y);

                if (cf != 0){
                    u.adj.add(v);
                    u.adjCapacity.put(v, cf);
                }

                if (f != 0) {
                    v.adj.add(u);
                    v.adjCapacity.put(u, f);
                }
            }
        }

        return residual;
    }

    public static void print(Vertex u, Vertex v){
        float f = 0;
        for (Vertex x : u.adj)
            f += u.adjFlow.get(x);
        System.out.println("\nValor do fluxo máximo entre " + u.name + " e " + v.name + ": " + f);
    }

    public static float getMinCapacityPath(Vertex drainf) {
        Vertex vf = drainf;
        Vertex uf = drainf.ancestor;
        float minCapacityf = Integer.MAX_VALUE;
        while (uf != null){
            if (minCapacityf > uf.adjCapacity.get(vf))
                minCapacityf = uf.adjCapacity.get(vf);

            vf = uf;
            uf = uf.ancestor;
        }
        return minCapacityf;
    }
}

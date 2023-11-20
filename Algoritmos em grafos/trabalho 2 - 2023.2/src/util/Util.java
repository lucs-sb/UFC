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

    public static List<Vertex> getChildren(List<Vertex> graph, String name){
        List<Vertex> children = new ArrayList<>();

        for (Vertex u : graph){
            if (u.ancestor != null && name.equals(u.ancestor.name))
                children.add(u);
        }

        return children;
    }

    public static void print(Vertex v, Vertex u){
        System.out.println("\nValor do menor caminho entre " + v.name + " e " + u.name + ": " + u.d);

        List<String> path = new ArrayList<>();

        if(u.ancestor == null)
            return;

        Vertex ancestor = u.ancestor;
        path.add(0, ancestor.name);
        while (ancestor.ancestor != null){
            ancestor = ancestor.ancestor;
            path.add(0, ancestor.name);
        }

        System.out.print("\nCaminho: ");
        for (String s : path)
            System.out.print(s + " -> ");
        System.out.print(u.name + "\n");
    }
}

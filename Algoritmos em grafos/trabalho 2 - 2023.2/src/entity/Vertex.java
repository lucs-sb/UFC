package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex {
    public String name;
    public float d;
    public boolean visit = false;
    public Vertex ancestor;
    public List<Vertex> adj = new ArrayList<>();
    public Map<Vertex, Float> adjValues = new HashMap<>();

    public Vertex(String name){
        this.name = name;
    }
}

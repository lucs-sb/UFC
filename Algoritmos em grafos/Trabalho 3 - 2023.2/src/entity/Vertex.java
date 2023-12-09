package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex {
    public String name;
    public boolean visit = false;
    public Vertex ancestor = null;
    public List<Vertex> adj = new ArrayList<>();
    public Map<Vertex, Float> adjFlow = new HashMap<>();
    public Map<Vertex, Float> adjCapacity = new HashMap<>();

    public Vertex(String name){
        this.name = name;
    }
}

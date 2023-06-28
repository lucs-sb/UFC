package grafo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    public String nome;
    public int chave;
    public Vertice pai;
    public Aresta aresta;
    public List<Vertice> adjacentes = new ArrayList<>();
    public List<Aresta> arestas = new ArrayList<>();

    public Vertice(String nome){
        this.nome = nome;
    }
}

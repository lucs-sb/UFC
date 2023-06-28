package itemA;

import grafo.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
    public List<Vertice> caminhoUV = new ArrayList<>();
    public Vertice inicio;
    public Vertice fim;
    public int peso;

    public Resultado(Vertice inicio, Vertice fim){
        this.inicio = inicio;
        this.fim = fim;
        this.peso = 0;
    }
}

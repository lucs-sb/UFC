package grafo;

public class Aresta {
    public int peso;
    public Vertice inicio;
    public Vertice fim;

    public Aresta(int peso) {
    }

    public Aresta(int peso, Vertice inicio, Vertice fim)
    {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }
}

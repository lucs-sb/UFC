
public class Main {
    public static void main(String[] args) {
        EdmondKarp.edmondKarp(grafoQuestao1());
        EdmondKarp.edmondKarp(grafoQuestao2());
    }

    private static Vertice[] grafoQuestao1(){
        Vertice[] grafo = new Vertice[6];

        for (int i = 0; i < grafo.length; i++)
            grafo[i] = new Vertice();

        Aresta sv1 = new Aresta(0, 1, 0, 16);
        Aresta v1s = new Aresta(1, 0, 0, 0);
        grafo[0].arestas.add(sv1);
        grafo[1].arestas.add(v1s);

        Aresta sv2 = new Aresta(0, 2, 0, 13);
        Aresta v2s = new Aresta(2, 0, 0, 0);
        grafo[0].arestas.add(sv2);
        grafo[2].arestas.add(v2s);

        Aresta v1v3 = new Aresta(1, 3, 0, 12);
        Aresta v3v1 = new Aresta(3, 1, 0, 0);
        grafo[1].arestas.add(v1v3);
        grafo[3].arestas.add(v3v1);

        Aresta v2v1 = new Aresta(2, 1, 0, 4);
        Aresta v1v2 = new Aresta(1, 2, 0, 0);
        grafo[2].arestas.add(v2v1);
        grafo[1].arestas.add(v1v2);

        Aresta v2v4 = new Aresta(2, 4, 0, 14);
        Aresta v4v2 = new Aresta(4, 2, 0, 0);
        grafo[2].arestas.add(v2v4);
        grafo[4].arestas.add(v4v2);

        Aresta v3v2 = new Aresta(3, 2, 0, 9);
        Aresta v2v3 = new Aresta(2, 3, 0, 0);
        grafo[3].arestas.add(v3v2);
        grafo[2].arestas.add(v2v3);

        Aresta v3t = new Aresta(3, 5, 0, 20);
        Aresta tv3 = new Aresta(5, 3, 0, 0);
        grafo[3].arestas.add(v3t);
        grafo[5].arestas.add(tv3);

        Aresta v4v3 = new Aresta(4, 3, 0, 7);
        Aresta v3v4 = new Aresta(3, 4, 0, 0);
        grafo[4].arestas.add(v4v3);
        grafo[3].arestas.add(v3v4);

        Aresta v4t = new Aresta(4, 5, 0, 4);
        Aresta tv4 = new Aresta(5, 4, 0, 0);
        grafo[4].arestas.add(v4t);
        grafo[5].arestas.add(tv4);

        return grafo;
    }

    private static Vertice[] grafoQuestao2(){
        Vertice[] grafo = new Vertice[7];

        for (int i = 0; i < grafo.length; i++)
            grafo[i] = new Vertice();

        Aresta sv1 = new Aresta(0, 1, 0, 15);
        Aresta v1s = new Aresta(1, 0, 0, 0);
        grafo[0].arestas.add(sv1);
        grafo[1].arestas.add(v1s);

        Aresta sv2 = new Aresta(0, 2, 0, 20);
        Aresta v2s = new Aresta(2, 0, 0, 0);
        grafo[0].arestas.add(sv2);
        grafo[2].arestas.add(v2s);

        Aresta sv3 = new Aresta(0, 3, 0, 10);
        Aresta v3s = new Aresta(3, 0, 0, 0);
        grafo[0].arestas.add(sv3);
        grafo[3].arestas.add(v3s);

        Aresta v1v4 = new Aresta(1, 4, 0, 2);
        Aresta v4v1 = new Aresta(4, 1, 0, 0);
        grafo[1].arestas.add(v1v4);
        grafo[4].arestas.add(v4v1);

        Aresta v1v5 = new Aresta(1, 5, 0, 3);
        Aresta v5v1 = new Aresta(5, 1, 0, 0);
        grafo[1].arestas.add(v1v5);
        grafo[5].arestas.add(v5v1);

        Aresta v2v4 = new Aresta(2, 4, 0, 4);
        Aresta v4v2 = new Aresta(4, 2, 0, 0);
        grafo[2].arestas.add(v2v4);
        grafo[4].arestas.add(v4v2);

        Aresta v2t = new Aresta(2, 6, 0, 15);
        Aresta tv2 = new Aresta(6, 2, 0, 0);
        grafo[2].arestas.add(v2t);
        grafo[6].arestas.add(tv2);

        Aresta v2v5 = new Aresta(2, 5, 0, 5);
        Aresta v5v2 = new Aresta(5, 2, 0, 0);
        grafo[2].arestas.add(v2v5);
        grafo[5].arestas.add(v5v2);

        Aresta v3v4 = new Aresta(3, 4, 0, 5);
        Aresta v4v3 = new Aresta(4, 3, 0, 0);
        grafo[3].arestas.add(v3v4);
        grafo[4].arestas.add(v4v3);

        Aresta v3v5 = new Aresta(3, 5, 0, 4);
        Aresta v5v3 = new Aresta(5, 3, 0, 0);
        grafo[3].arestas.add(v3v5);
        grafo[5].arestas.add(v5v3);

        Aresta v4t = new Aresta(4, 6, 0, 15);
        Aresta tv4 = new Aresta(6, 4, 0, 0);
        grafo[4].arestas.add(v4t);
        grafo[6].arestas.add(tv4);

        Aresta v5t = new Aresta(5, 6, 0, 10);
        Aresta tv5 = new Aresta(6, 5, 0, 0);
        grafo[5].arestas.add(v5t);
        grafo[6].arestas.add(tv5);

        return grafo;
    }
}
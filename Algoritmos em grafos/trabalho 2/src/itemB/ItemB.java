package itemB;

import grafo.Aresta;
import grafo.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemB {
    public static void iniciar(){
        List<Vertice> grafo = criarGrafo();
        Collections.shuffle(grafo);
        bfs(grafo);
    }

    private static void bfs(List<Vertice> grafo){
        List<Vertice> vertices = new ArrayList<>(grafo);
        List<Aresta> mst = new ArrayList<>();
        while(!vertices.isEmpty()){
            Vertice u = vertices.remove(0);
            for (Aresta uv : u.arestas){
                if (!mst.contains(uv)){
                    mst.add(uv);
                    formouCiclo(mst, uv);
                }
            }
            vertices.remove(u);
        }
        System.out.println("\nItem B: ");
        mst.forEach(ab -> System.out.print(ab.inicio.nome + " -> " + ab.fim.nome + " , "));
    }

    private static void formouCiclo(List<Aresta> mst, Aresta uv){
        List<Vertice> vertices = new ArrayList<>();
        for (Aresta ab : mst){
            if (!vertices.contains(ab.inicio))
                vertices.add(ab.inicio);
            if (!vertices.contains(ab.fim))
                vertices.add(ab.fim);
        }
        if (mst.size() >= vertices.size())
            removerCiclo(mst, vertices, uv);
    }

    private static void removerCiclo(List<Aresta> mst, List<Vertice> vertices, Aresta uv){
        List<Aresta> ciclo = pegarCiclo(vertices, mst, uv);

        Aresta aresta = new Aresta(-1);
        for (Aresta ab : ciclo)
            if (ab.peso > aresta.peso)
                aresta = ab;

        mst.remove(aresta);
    }

    private static List<Aresta> pegarCiclo(List<Vertice> grafo, List<Aresta> mst, Aresta uv){
        gerarTrilha(grafo, mst, uv.inicio, uv.fim);
        List<Aresta> ciclo = new ArrayList<>();
        pegarCaminho(ciclo, uv.fim);
        ciclo.add(uv);
        return ciclo;
    }

    public static void gerarTrilha(List<Vertice> grafo, List<Aresta> mst, Vertice fonte, Vertice destino){
        for(Vertice u : grafo)
            u.pai = null;

        List<Vertice> descobertos = new ArrayList<>();
        descobertos.add(fonte);

        while(!descobertos.isEmpty()){
            Vertice u = descobertos.remove(0);
            for(Vertice v : u.adjacentes){
                if ((u.nome.equals(fonte.nome) && v.nome.equals(destino.nome)))
                    continue;
                if(v.pai == null && !v.nome.equals(fonte.nome))
                    for (Aresta uv : u.arestas) {
                        if (!mst.contains(uv))
                            continue;
                        if (uv.inicio.nome.equals(v.nome) || uv.fim.nome.equals(v.nome)) {
                            v.pai = u;
                            v.aresta = uv;
                            descobertos.add(v);
                            break;
                        }
                    }
            }
        }
    }

    public static void pegarCaminho(List<Aresta> ciclo, Vertice v){
        if(v.pai != null) {
            ciclo.add(v.aresta);
            pegarCaminho(ciclo, v.pai);
        }
    }

    private static List<Vertice> criarGrafo(){
        List<Vertice> grafo = new ArrayList<>();
        Vertice a = new Vertice ("a");
        Vertice b = new Vertice ("b");
        Vertice c = new Vertice ("c");
        Vertice d = new Vertice ("d");
        Vertice e = new Vertice ("e");
        Vertice f = new Vertice ("f");
        Vertice g = new Vertice ("g");
        Vertice h = new Vertice ("h");
        Vertice i = new Vertice ("i");
        Aresta ab = new Aresta (4, a, b);
        Aresta ah = new Aresta (8, a, h);

        Aresta bc = new Aresta (8, b, c);
        Aresta bh = new Aresta (11, b, h);

        Aresta cd = new Aresta (7, c, d);
        Aresta ci = new Aresta (2, c, i);
        Aresta cf = new Aresta (4, c, f);

        Aresta de = new Aresta (9, d, e);
        Aresta df = new Aresta (14, d, f);

        Aresta ef = new Aresta (10, e, f);

        Aresta fg = new Aresta (2, f, g);

        Aresta gi = new Aresta (6, g, i);
        Aresta gh = new Aresta (1, g, h);

        Aresta hi = new Aresta (7, h, i);

        List<Aresta> arestasA = new ArrayList<>();
        arestasA.add(ab);
        arestasA.add(ah);
        a.arestas = arestasA;
        a.adjacentes.add(b);
        a.adjacentes.add(h);

        List<Aresta> arestasB = new ArrayList<>();
        arestasB.add(ab);
        arestasB.add(bc);
        arestasB.add(bh);
        b.arestas = arestasB;
        b.adjacentes.add(a);
        b.adjacentes.add(c);
        b.adjacentes.add(h);

        List<Aresta> arestasC = new ArrayList<>();
        arestasC.add(bc);
        arestasC.add(ci);
        arestasC.add(cf);
        arestasC.add(cd);
        c.arestas = arestasC;
        c.adjacentes.add(b);
        c.adjacentes.add(i);
        c.adjacentes.add(f);
        c.adjacentes.add(d);

        List<Aresta> arestasD = new ArrayList<>();
        arestasD.add(cd);
        arestasD.add(de);
        arestasD.add(df);
        d.arestas = arestasD;
        d.adjacentes.add(e);
        d.adjacentes.add(c);
        d.adjacentes.add(f);

        List<Aresta> arestasE = new ArrayList<>();
        arestasE.add(de);
        arestasE.add(ef);
        e.arestas = arestasE;
        e.adjacentes.add(d);
        e.adjacentes.add(f);

        List<Aresta> arestasF = new ArrayList<>();
        arestasF.add(fg);
        arestasF.add(cf);
        arestasF.add(df);
        arestasF.add(ef);
        f.arestas = arestasF;
        f.adjacentes.add(g);
        f.adjacentes.add(c);
        f.adjacentes.add(d);
        f.adjacentes.add(e);

        List<Aresta> arestasG = new ArrayList<>();
        arestasG.add(gh);
        arestasG.add(gi);
        arestasG.add(fg);
        g.arestas = arestasG;
        g.adjacentes.add(i);
        g.adjacentes.add(f);
        g.adjacentes.add(h);

        List<Aresta> arestasH = new ArrayList<>();
        arestasH.add(ah);
        arestasH.add(hi);
        arestasH.add(gh);
        h.arestas = arestasH;
        h.adjacentes.add(a);
        h.adjacentes.add(i);
        h.adjacentes.add(g);

        List<Aresta> arestasI = new ArrayList<>();
        arestasI.add(ci);
        arestasI.add(hi);
        arestasI.add(gi);
        i.arestas = arestasI;
        i.adjacentes.add(g);
        i.adjacentes.add(c);
        i.adjacentes.add(h);

        grafo.add(a);
        grafo.add(b);
        grafo.add(c);
        grafo.add(d);
        grafo.add(e);
        grafo.add(f);
        grafo.add(g);
        grafo.add(h);
        grafo.add(i);

        return grafo;
    }
}

package itemA;

import grafo.Aresta;
import grafo.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemA {
    public static void iniciar(){
        List<Vertice> grafo = criarGrafo();
        Collections.shuffle(grafo);

        mstPrim(grafo, grafo.get(0));
        Resultado resultado1 = new Resultado(grafo.get(0), grafo.get(7));
        buscaCaminho(resultado1, resultado1.fim, false);

        mstPrim(grafo, grafo.get(7));
        Resultado resultado2 = new Resultado(grafo.get(0), grafo.get(7));
        buscaCaminho(resultado2, resultado2.inicio, true);

        Resultado resultado = resultado1.peso < resultado2.peso ? resultado1 : resultado2;

        System.out.println("Item A: ");
        System.out.print("Menor caminho: ");
        System.out.print(resultado.inicio.nome + " -> ");
        for(int i = 1; i < resultado.caminhoUV.size(); i++){
            System.out.print(resultado.caminhoUV.get(i).nome + " -> ");
        }
        System.out.println(resultado.fim.nome);
        System.out.println("Peso: " + resultado.peso);
    }

    private static void mstPrim(List<Vertice> grafo, Vertice r){
        for (Vertice u : grafo){
            u.chave = Integer.MAX_VALUE;
            u.pai = null;
        }
        r.chave = 0;
        List<Vertice> q = new ArrayList<>(grafo);
        while(!q.isEmpty()){
            Vertice u = pegarMinimo(q);
            for(Vertice v : u.adjacentes){
                if(q.contains(v) && eMenor(u, v)){
                    v.pai = u;
                }
            }
            q.remove(u);
        }
    }

    private static void buscaCaminho(Resultado resultado, Vertice u, boolean inicio){
        if (u != null && (!inicio && resultado.inicio.nome.equals(u.nome) || inicio && resultado.fim.nome.equals(u.nome)))
            return;

        if (u != null) {
            resultado.peso += u.chave;
            resultado.caminhoUV.add(u);
        }

        buscaCaminho(resultado, u.pai, inicio);
    }

    private static Vertice pegarMinimo(List<Vertice> lista){
        Vertice aux = new Vertice("");
        int min = Integer.MAX_VALUE;
        for(Vertice u : lista){
            if(min > u.chave){
                min = u.chave;
                aux = u;
            }
        }
        return aux;
    }

    private static boolean eMenor(Vertice u, Vertice v){
        for(Aresta uv : u.arestas){
            if((uv.inicio.nome.equals(v.nome) || uv.fim.nome.equals(v.nome)) && uv.peso < v.chave){
                v.chave = uv.peso;
                return true;
            }
        }
        return false;
    }

    private static List <Vertice> criarGrafo(){
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

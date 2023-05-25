import java.util.*;
public class Main
{
    static class Vertice
    {
        public String nome;
        public String cor;
        public int d;
        public Vertice pai;
        public List<Vertice> adjacentes = new ArrayList <> ();
        public Vertice(String nome){
            this.nome = nome;
            this.d = -1;
            this.pai = null;
        }
    }
    public static void main (String[]args){

        Vertice p = new Vertice ("p");
        Vertice q = new Vertice ("q");
        Vertice r = new Vertice ("r");
        Vertice s = new Vertice ("s");
        Vertice t = new Vertice ("t");
        Vertice u = new Vertice ("u");
        /*Vertice v = new Vertice ("v");
        Vertice w = new Vertice ("w");
        Vertice x = new Vertice ("x");
        Vertice y = new Vertice ("y");
        Vertice z = new Vertice ("z");
        p.adjacentes.add(q);
        p.adjacentes.add(r);
        p.adjacentes.add(s);
        q.adjacentes.add(p);
        r.adjacentes.add(p);
        s.adjacentes.add(p);
        s.adjacentes.add(t);
        s.adjacentes.add(u);
        t.adjacentes.add(s);
        t.adjacentes.add(v);
        u.adjacentes.add(p);
        u.adjacentes.add(w);
        v.adjacentes.add(t);
        v.adjacentes.add(x);
        w.adjacentes.add(u);
        w.adjacentes.add(y);
        x.adjacentes.add(v);
        x.adjacentes.add(z);
        y.adjacentes.add(w);
        y.adjacentes.add(z);
        z.adjacentes.add(x);
        z.adjacentes.add(y);
        List<Vertice> grafo = new ArrayList<>();
        grafo.add(p);
        grafo.add(q);
        grafo.add(r);
        grafo.add(s);
        grafo.add(t);
        grafo.add(u);
        grafo.add(v);
        grafo.add(w);
        grafo.add(x);
        grafo.add(y);
        grafo.add(z);*/
        p.adjacentes.add(r);
        p.adjacentes.add(s);
        q.adjacentes.add(s);
        q.adjacentes.add(r);
        q.adjacentes.add(t);
        r.adjacentes.add(p);
        r.adjacentes.add(q);
        s.adjacentes.add(p);
        s.adjacentes.add(q);
        s.adjacentes.add(u);
        t.adjacentes.add(q);
        u.adjacentes.add(s);
        List<Vertice> grafo = new ArrayList<>();
        grafo.add(p);
        grafo.add(q);
        grafo.add(r);
        grafo.add(s);
        grafo.add(t);
        grafo.add(u);
        //bfs(grafo, s);
        if (bfsParaCiclo(grafo, 6))
            System.out.println("Contém ciclo");
        else
            System.out.println("Não contém ciclo");
    }
    static void bfs(List<Vertice> grafo, Vertice s){
        for(Vertice u : grafo)
            if (!s.nome.equals(u.nome)){
                u.cor = "branco";
                u.d = -1;
                u.pai = null;
            }
        s.cor = "cinza";
        s.d = 0;
        s.pai = null;
        List<Vertice> Q = new ArrayList<>();
        Q.add(s);
        while(!Q.isEmpty()){
            Vertice u = Q.remove(0);
            for(Vertice v : u.adjacentes)
                if("branco".equals(v.cor)){
                    v.cor = "cinza";
                    v.d = u.d + 1;
                    v.pai = u;
                    Q.add(v);
                }
            u.cor = "preto";
        }
    }

    static boolean bfsParaCiclo(List<Vertice> grafo, Vertice s){
        for(Vertice u : grafo)
            if (!s.nome.equals(u.nome)){
                u.cor = "branco";
                u.d = -1;
                u.pai = null;
            }
        s.cor = "cinza";
        s.d = 0;
        s.pai = null;
        List<Vertice> Q = new ArrayList<>();
        Q.add(s);
        while(!Q.isEmpty()){
            Vertice u = Q.remove(0);
            for(Vertice v : u.adjacentes){
                if(v.pai != null && u.pai != null && u.pai.nome.equals(v.pai.nome))
                    return true;
                if("branco".equals(v.cor)){
                    v.cor = "cinza";
                    v.d = u.d + 1;
                    v.pai = u;
                    Q.add(v);
                }
                else
                    (u.pai != v && procuraCiclo(u.pai, v.pai))
                        return true;
            }
            u.cor = "preto";

        }
        return false;
    }

    static boolean bfsParaCiclo(List<Vertice> grafo, int qtdVertices){
        Random ramdom = new Random();
        for(Vertice u : grafo)
        {
            u.cor = "branco";
            u.d = -1;
            u.pai = null;
        }
        List<Vertice> Q = new ArrayList<>();
        Q.add(grafo.get(ramdom.nextInt(qtdVertices)));
        while(!Q.isEmpty()){
            Vertice u = Q.remove(0);
            for(Vertice v : u.adjacentes){
                if(v.pai != null && u.pai != null && u.pai.nome.equals(v.pai.nome))
                    return true;
                if("branco".equals(v.cor)){
                    v.cor = "cinza";
                    v.d = u.d + 1;
                    v.pai = u;
                    Q.add(v);
                }
                else
                (u.pai != v && procuraCiclo(u.pai, v.pai))
                    return true;
            }
            u.cor = "preto";

        }
        return false;
    }

    static boolean procuraCiclo(Vertice u, Vertice v){
        
        if(v == null || u ==null)
            return false;

        if (u.pai != null && v.pai != null)
            return procuraCiclo(u.pai, v.pai);
        else if(u.pai != null && v.pai == null)
            return procuraCiclo(u.pai, v);
        else if(u.pai == null && v.pai != null)
            return procuraCiclo(u, v.pai);
        
        if(u.nome.equals(v.nome))
            return true;

        return false;
    }

    /*static boolean procuraCiclo(Vertice u, Vertice v){
        if (u == null || v == null)
            return false;

        if (v.pai != null && !v.pai.nome.equals(u.nome))
            procuraCiclo(u.pai, v.pai);

        return true;
    }*/
}

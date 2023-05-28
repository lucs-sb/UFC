import java.util.*;
public class Main
{
    public static void main (String[]args){
        List<Vertice> grafo = gerarGrafoG();
        //List<Vertice> grafo = gerarGrafoGLinha();
        
        for(int i = 0; i < grafo.size(); i++){
            if (bfs(grafo, grafo.get(i)))
                System.out.println("Contém ciclo");
            else
                System.out.println("Não contém ciclo");
        }
        
    }

    public static boolean bfs(List<Vertice> grafo, Vertice fonte){
        for(Vertice u : grafo)
            if (!fonte.nome.equals(u.nome)){
                u.cor = "branco";
                u.d = -1;
                u.pai = null;
            }

        fonte.cor = "cinza";
        fonte.d = 0;
        fonte.pai = null;

        List<Vertice> descobertos = new ArrayList<>();
        descobertos.add(fonte);

        while(!descobertos.isEmpty()){
            Vertice u = descobertos.remove(0);
            for(Vertice v : u.adjacentes){
                if(v.pai != null && u.pai != null && u.pai.nome.equals(v.pai.nome))
                    return true;

                if("branco".equals(v.cor)){
                    v.cor = "cinza";
                    v.d = u.d + 1;
                    v.pai = u;
                    descobertos.add(v);
                }
                else
                    if(u.pai != v && procuraCiclo(u.pai, v.pai))
                        return true;
            }
            u.cor = "preto";
        }

        return false;
    }

   public static boolean procuraCiclo(Vertice u, Vertice v){
        if(u == null || v == null)
            return false;

        if (u.pai != null && v.pai != null)
            return procuraCiclo(u.pai, v.pai);
        else if(u.pai != null)
            return procuraCiclo(u.pai, v);
        else if(v.pai != null)
            return procuraCiclo(u, v.pai);
        
        if(u.nome.equals(v.nome))
            return true;

        return false;
    }

   public static List<Vertice> gerarGrafoG(){
        Vertice u = new Vertice("u");
        Vertice v = new Vertice("v");
        Vertice w = new Vertice("w");
        Vertice x = new Vertice("x");
        Vertice y = new Vertice("y");

        u.adjacentes.add(v);
        u.adjacentes.add(y);
        u.adjacentes.add(x);

        v.adjacentes.add(u);
        v.adjacentes.add(w);

        w.adjacentes.add(v);
        w.adjacentes.add(x);

        x.adjacentes.add(w);
        x.adjacentes.add(y);
        x.adjacentes.add(u);

        y.adjacentes.add(u);
        y.adjacentes.add(x);

        List<Vertice> grafo = new ArrayList<>();
        grafo.add(u);
        grafo.add(v);
        grafo.add(w);
        grafo.add(x);
        grafo.add(y);

        return grafo;
    }

   public static List<Vertice> gerarGrafoGLinha(){
        Vertice u = new Vertice("u");
        Vertice v = new Vertice("v");
        Vertice w = new Vertice("w");
        Vertice x = new Vertice("x");
        Vertice y = new Vertice("y");

        u.adjacentes.add(w);

        v.adjacentes.add(y);
        v.adjacentes.add(x);

        w.adjacentes.add(u);
        w.adjacentes.add(y);

        x.adjacentes.add(v);

        y.adjacentes.add(w);
        y.adjacentes.add(v);

        List<Vertice> grafo = new ArrayList<>();
        grafo.add(u);
        grafo.add(v);
        grafo.add(w);
        grafo.add(x);
        grafo.add(y);

        return grafo;
    }
}

import java.util.*;
public class Main
{
    public static void main (String[]args){
        List<Vertice> grafo = gerarGrafoSemCiclo();
        //List<Vertice> grafo = gerarGrafoComCiclo();
        
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

   public static List<Vertice> gerarGrafoSemCiclo(){
        Vertice a = new Vertice("a");
        Vertice b = new Vertice("b");
        Vertice x = new Vertice("x");
        Vertice y = new Vertice("y");
        Vertice z = new Vertice("z");

        a.adjacentes.add(b);
        a.adjacentes.add(z);

        b.adjacentes.add(x);
        b.adjacentes.add(a);

        x.adjacentes.add(b);

        y.adjacentes.add(z);

        z.adjacentes.add(a);
        z.adjacentes.add(y);

        List<Vertice> grafo = new ArrayList<>();
        grafo.add(a);
        grafo.add(b);
        grafo.add(x);
        grafo.add(y);
        grafo.add(z);

        return grafo;
    }

   public static List<Vertice> gerarGrafoComCiclo(){
       Vertice a = new Vertice("a");
       Vertice b = new Vertice("b");
       Vertice x = new Vertice("x");
       Vertice y = new Vertice("y");
       Vertice z = new Vertice("z");

       a.adjacentes.add(b);
       a.adjacentes.add(z);

       b.adjacentes.add(x);
       b.adjacentes.add(a);

       x.adjacentes.add(b);
       x.adjacentes.add(y);

       y.adjacentes.add(z);
       y.adjacentes.add(x);

       z.adjacentes.add(a);
       z.adjacentes.add(y);

       List<Vertice> grafo = new ArrayList<>();
       grafo.add(a);
       grafo.add(b);
       grafo.add(x);
       grafo.add(y);
       grafo.add(z);

        return grafo;
    }
}

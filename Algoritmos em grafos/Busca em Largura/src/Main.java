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
        Scanner input = new Scanner(System.in);
        List<Vertice> grafo = new ArrayList<>();
        Vertice v;
        
        for(int i = 97; i < 106; i++)
            grafo.add(new Vertice(Character.toString(i)));
            
        for(int i = 0; i < grafo.size(); i++){
            v = grafo.get(i);
            System.out.println(v.nome);
            String nome = "";
            while(!nome.equals("pronto")){
                nome = input.next();
                for(int j = 0; j < grafo.size(); j++)
                    if(grafo.get(j).nome.equals(nome)){
                        v.adjacentes.add(grafo.get(j));
                        break;
                    }
            }
        }
        
        for(int h = 0; h < grafo.size(); h++){
            System.out.print("\n" + grafo.get(h).nome + ": ");
            grafo.get(h).adjacentes.forEach(e -> {
                System.out.print(e.nome + ", ");
            });
        }
        
        for(int x = 0; x < grafo.size(); x++){
            if (bfsParaCiclo(grafo, grafo.get(x)))
                System.out.println("Contém ciclo");
            else
                System.out.println("Não contém ciclo");
        }
        
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

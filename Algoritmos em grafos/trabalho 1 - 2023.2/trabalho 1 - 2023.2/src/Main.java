import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main
{
    public static void main (String[]args)throws IOException{
        List<Vertice> grafo = buscarGrafo();
        List<Vertice> ciclo;

        for(int i = 0; i < grafo.size(); i++){
            ciclo = bfs(grafo, grafo.get(i));
            if (ciclo.size() != 0) {
                System.out.print("Partindo do vértice " + grafo.get(i).nome + " contém esse ciclo: ");
                System.out.print(ciclo.get(ciclo.size()-2).nome + " ->0 ");
                String x = "";
                String z;
                for (int k = 0; k < ciclo.size()-2; k+=2)
                    System.out.print(ciclo.get(k).nome + " -> ");
                for (int j = 1; j < ciclo.size()-2; j+=2) {
                    z = x;
                    x = ciclo.get(j).nome + " -> " + z;
                }
                System.out.print(x);
                System.out.print(ciclo.get(ciclo.size()-1).nome + " -> " + ciclo.get(ciclo.size()-2).nome);
                break;
            }
            else
                System.out.println("Partindo do vértice " + grafo.get(i).nome + " não contém ciclo");
        }

    }

    private static List<Vertice> buscarGrafo()throws IOException {
        Path path = Paths.get("C:/Users/soare/OneDrive/Área de Trabalho/grafo.txt");
        List<String> linhasArquivo = Files.lines(path).toList();
        List<Vertice> grafo = new ArrayList<>();

        for (int i = 1; i <= Integer.parseInt(linhasArquivo.get(0)); i++){
            String[] v = linhasArquivo.get(i).split(" ");
            grafo.add(new Vertice(v[0]));
        }

        for(int j = 1; j < linhasArquivo.size(); j++){
            String[] v = linhasArquivo.get(j).split(" ");
            Vertice x = buscarVertice(grafo, v[0]);
            for (int k = 1; k < v.length; k++) {
                assert x != null;
                x.adjacentes.add(buscarVertice(grafo, v[k]));
            }
        }

        return grafo;
    }

    private static Vertice buscarVertice(List<Vertice> grafo, String nome){
        for (Vertice vertice : grafo)
            if (nome.equals(vertice.nome))
                return vertice;

        return null;
    }

    public static List<Vertice> bfs(List<Vertice> grafo, Vertice fonte){
        for(Vertice u : grafo)
            if (!fonte.nome.equals(u.nome)){
                u.cor = "branco";
                u.d = Integer.MAX_VALUE;
                u.pai = null;
            }

        fonte.cor = "cinza";
        fonte.d = 0;
        fonte.pai = null;

        List<Vertice> ciclo = new ArrayList<>();
        List<Vertice> descobertos = new ArrayList<>();
        descobertos.add(fonte);

        while(!descobertos.isEmpty()){
            Vertice u = descobertos.remove(0);
            for(Vertice v : u.adjacentes){
                if("branco".equals(v.cor)){
                    v.cor = "cinza";
                    v.d = u.d + 1;
                    v.pai = u;
                    descobertos.add(v);
                }
                else
                if(u.pai != v && procuraCiclo(u.pai, v.pai, ciclo).size() != 0) {
                    ciclo.add(u);
                    ciclo.add(v);
                    return ciclo;
                }
            }
            u.cor = "preto";
        }

        return ciclo;
    }

    public static List<Vertice> procuraCiclo(Vertice u, Vertice v, List<Vertice> ciclo){
        if(u == null || v == null) {
            ciclo.clear();
            return ciclo;
        }

        if (!ciclo.contains(u))
            ciclo.add(u);

        if (!ciclo.contains(v))
            ciclo.add(v);

        if (u.nome.equals(v.nome))
            return ciclo;

        if (u.pai != null && v.pai != null)
            return procuraCiclo(u.pai, v.pai, ciclo);
        else if(u.pai != null)
            return procuraCiclo(u.pai, v, ciclo);
        else if(v.pai != null)
            return procuraCiclo(u, v.pai, ciclo);

        ciclo.clear();
        return ciclo;
    }
}
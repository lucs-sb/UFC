import java.util.ArrayList;
import java.util.List;

public class EdmondKarp {
    public static void edmondKarp(Vertice[] grafo){
        int fonte = 0;
        int ultimoVertice = grafo.length - 1;
        int fluxoMaximo = 0;

        while (true){
            Aresta[] caminhoAtual = new Aresta[grafo.length];

            List<Vertice> q = new ArrayList<>();
            q.add(grafo[fonte]);

            while (!q.isEmpty()){
                Vertice verticeAtual = q.remove(0);

                for (Aresta uv : verticeAtual.arestas)
                    if (caminhoAtual[uv.v] == null && uv.v != fonte && uv.capacidade > uv.fluxo){
                        caminhoAtual[uv.v] = uv;
                        q.add(grafo[uv.v]);
                    }
            }

            if (caminhoAtual[ultimoVertice] == null)
                break;

            int fluxoCorrente = Integer.MAX_VALUE;

            for (Aresta uv = caminhoAtual[ultimoVertice]; uv != null; uv = caminhoAtual[uv.u])
                fluxoCorrente = Math.min(fluxoCorrente, uv.capacidade - uv.fluxo);

            for (Aresta uv = caminhoAtual[ultimoVertice]; uv != null; uv = caminhoAtual[uv.u])
                uv.fluxo += fluxoCorrente;

            fluxoMaximo += fluxoCorrente;
        }

        for (Vertice u : grafo)
            for (Aresta uv : u.arestas)
                if (grafo[uv.u] == u && uv.fluxo != 0)
                    System.out.println("Fluxo do vértice " + uv.u + " para o vértice " + uv.v + ": " + uv.fluxo + "/" + uv.capacidade);

        System.out.println("\nFluxo máximo: " + fluxoMaximo + "\n");
    }
}

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    public String nome;
    public String cor;
    public int d;
    public Vertice pai;
    public List<Vertice> adjacentes = new ArrayList<>();
    public Vertice(String nome){
        this.nome = nome;
    }
}

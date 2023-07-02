package service.heaps;

public class LeftistHeap {
    public static Heap union(Heap h1, Heap h2) {
        if(h1 == null) return h2;
        if(h2 == null) return h1;
        if(h1.order.priority < h2.order.priority)
            return unionHeaps(h1, h2);
        else
            return unionHeaps(h2, h1);
    }

    public static Heap removal(Heap h){
        return union(h.dir, h.esq);
    }

    private static Heap unionHeaps(Heap h1, Heap h2){
        if(h1.esq == null)
            h1.esq = h2;
        else{
            h1.dir = union(h1.dir, h2);
            if(h1.esq.s < h1.dir.s)
                exchangeChildren(h1);
            h1.s = h1.dir.s+1;
        }
        return h1;
    }

    private static void exchangeChildren(Heap a){
        Heap aux = a.esq;
        a.esq = a.dir;
        a.dir = aux;
    }
}

package service.heaps;

import entity.Order;

public class Heap {
    public Heap esq;
    public Heap dir;
    public Order order;
    public int s = 1;

    public Heap(Order order) {
        this.order = order;
    }
}

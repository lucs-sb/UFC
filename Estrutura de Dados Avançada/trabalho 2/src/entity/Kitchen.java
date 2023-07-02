package entity;

public class Kitchen {
    public Order[] simpleHeap;
    public int tail;

    public Kitchen(int capacity){
        this.simpleHeap = new Order[10];
        this.tail = -1;
    }
}

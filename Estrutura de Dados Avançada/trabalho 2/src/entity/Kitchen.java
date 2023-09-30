package entity;

public class Kitchen {
    public Order[] simpleHeap;
    public int tail;
    public int ordersPlaced;

    public Kitchen(int capacity){
        this.simpleHeap = new Order[capacity];
        this.tail = -1;
        this.ordersPlaced = 0;
    }
}

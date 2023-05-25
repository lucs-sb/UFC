package service.hash;

public abstract class HashTable {
    protected int max;
    protected int size;

    protected HashTable(int max) {
        this.max = max;
        this.size = 0;
    }

    protected int hashing(long key){ return (int) (key % this.max); }

    protected int doubleHash(int i){
        int w = 1 + (i % (this.max - 1));
        return (i + w) % this.max;
    }

    protected boolean calculateOccupancyRate(){
        return this.size >= 0.8 * this.max;
    }
}

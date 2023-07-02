package service.heaps;

import entity.Kitchen;
import entity.Order;

public class SimpleHeap {
    private static int left(int index) {
        return 2 * index + 1;
    }

    private static int right(int index) {
        return 2 * (index + 1);
    }

    private static int parent(int index) {
        return (index-1)/2;
    }

    public static void add(Kitchen kitchen, Order newOrder) {
        if (kitchen.tail >= (kitchen.simpleHeap.length - 1))
            resize(kitchen);

        kitchen.tail += 1;
        kitchen.simpleHeap[kitchen.tail] = newOrder;

        int i = kitchen.tail;
        while (i > 0 && kitchen.simpleHeap[parent(i)].priority > kitchen.simpleHeap[i].priority) {
            Order aux = kitchen.simpleHeap[i];
            kitchen.simpleHeap[i] = kitchen.simpleHeap[parent(i)];
            kitchen.simpleHeap[parent(i)] = aux;
            i = parent(i);
        }
    }

    public  static void remove(Kitchen kitchen) {
        if (kitchen.tail != -1){
            kitchen.simpleHeap[0] = kitchen.simpleHeap[kitchen.tail];
            kitchen.tail -= 1;

            heapify(kitchen, 0);
        }
    }

    private static void heapify(Kitchen kitchen, int index) {
        if (isLeaf(index, kitchen.tail) || !isValidIndex(index, kitchen.tail))
            return;

        int index_min = min_index(kitchen, index, left(index), right(index));

        if (index_min != index) {
            swap(kitchen, index, index_min);
            heapify(kitchen, index_min);
        }
    }

    private static int min_index(Kitchen kitchen, int index, int left, int right) {
        if (kitchen.simpleHeap[index].priority < kitchen.simpleHeap[left].priority) {
            if (isValidIndex(right, kitchen.tail)) {
                if (kitchen.simpleHeap[index].priority > kitchen.simpleHeap[right].priority)
                    return right;
            }
            return index;
        } else {
            if (isValidIndex(right, kitchen.tail)) {
                if (kitchen.simpleHeap[left].priority > kitchen.simpleHeap[right].priority)
                    return right;
            }
            return left;
        }
    }

    private static boolean isValidIndex(int index, int tail) {
        return index >= 0 && index <= tail;
    }

    private static boolean isLeaf(int index, int tail) {
        return index > parent(tail) && index <= tail;
    }

    private static void swap(Kitchen kitchen, int i, int j) {
        Order aux = kitchen.simpleHeap[i];
        kitchen.simpleHeap[i] = kitchen.simpleHeap[j];
        kitchen.simpleHeap[j] = aux;
    }

    private static void resize(Kitchen kitchen){
        Order[] aux = new Order[kitchen.tail*2];
        System.arraycopy(kitchen.simpleHeap, 0, aux, 0, kitchen.simpleHeap.length);
        kitchen.simpleHeap = aux;
    }
}

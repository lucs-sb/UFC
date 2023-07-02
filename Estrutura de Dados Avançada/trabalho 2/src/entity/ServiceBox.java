package entity;

import service.heaps.Heap;

public class ServiceBox {
    public String name;
    public boolean isPriority;
    public boolean isOpen;
    public Heap leftistHeap;

    public ServiceBox(String name, boolean isPriority, boolean isOpen) {
        this.name = name;
        this.isPriority = isPriority;
        this.isOpen = isOpen;
    }
}

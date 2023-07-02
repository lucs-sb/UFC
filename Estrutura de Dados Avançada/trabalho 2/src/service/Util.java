package service;

import entity.Kitchen;
import entity.ServiceBox;

public class Util {
    public static void printServiceBoxes(ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes){
        for (ServiceBox serviceBox : serviceBoxes)
            if(serviceBox.isOpen)
                System.out.print("\t\t" + serviceBox.name + "\t|");
        System.out.print("\n");
        for (ServiceBox serviceBox : serviceBoxes)
            if(serviceBox.isOpen && serviceBox.leftistHeap != null && serviceBox.leftistHeap.order != null)
                System.out.print("\t\t" + serviceBox.leftistHeap.order.clientName + "\t|");
            else
                System.out.print("\t\t\t\t|");
        System.out.print("\n");
        for (ServiceBox priorityServiceBox : priorityServiceBoxes)
            if(priorityServiceBox.isOpen)
                System.out.print("\t\t" + priorityServiceBox.name + "\t|");
        System.out.print("\n");
        for (ServiceBox priorityServiceBox : priorityServiceBoxes)
            if(priorityServiceBox.isOpen && priorityServiceBox.leftistHeap != null && priorityServiceBox.leftistHeap.order != null)
                System.out.print("\t\t\t" + priorityServiceBox.leftistHeap.order.clientName + "\t|");
            else
                System.out.print("\t\t\t\t|");
    }

    public static void printOrders(Kitchen kitchen){
        System.out.print("\n\t\tPróximos pedidos: \n");
        if (kitchen.tail != -1){
            for (int i = 0; i < kitchen.tail+1 && i < 3; i++) {
                System.out.print("\t\t" + kitchen.simpleHeap[i].priority + " - " + kitchen.simpleHeap[i].identifier + "\t\n");
            }
        }
    }

    public static int size(ServiceBox[] serviceBoxes){
        int counter = 0;
        for (ServiceBox serviceBox : serviceBoxes)
            if (serviceBox.isOpen)
                counter++;
        return counter;
    }
}

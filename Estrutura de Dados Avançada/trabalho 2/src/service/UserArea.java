package service;

import entity.Kitchen;
import entity.Order;
import entity.ServiceBox;
import service.heaps.Heap;
import service.heaps.LeftistHeap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class UserArea {
    public static void menu(List<Order> orders, ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes, Kitchen kitchen){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int op = -1;
        try {
            while (op != 0){
                System.out.print("\n\n\n\n\t\t\tLANCHONETE\n\n");
                Util.printServiceBoxes(serviceBoxes, priorityServiceBoxes);
                Util.printOrders(kitchen);
                System.out.print("\n\n\t\t\tMENU DE OPÇÕES\n\n");
                System.out.print("\t1 - Pegar tablet para escolha de pedido\n \t2 - Registrar pedido\n \t0 - Voltar\n");
                System.out.print("\nDigite uma opção: ");
                op = Integer.parseInt(in.readLine());
                switch (op){
                    case 1 -> chooseOrder(orders);
                    case 2 -> registerOrder(orders, serviceBoxes, priorityServiceBoxes);
                    case 0 -> {}
                    default -> System.out.println("Opção inválida");
                }
            }
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }

    private static void chooseOrder(List<Order> orders){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nDigite seu nome: ");
            String name = in.readLine();
            System.out.print("Seu atendimento é prioritário ? \nDigite true ou false\n");
            boolean isPriority = Boolean.parseBoolean(in.readLine());
            Order newOrder = new Order(name, isPriority, Calendar.getInstance());
            System.out.print("\n\nO identificador do seu pedido: " + newOrder.identifier);
            orders.add(newOrder);
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }

    private static void registerOrder(List<Order> orders, ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();
        try {
            System.out.print("\nDigite o identificador do seu pedido: ");
            String identifier = in.readLine();
            Order order = null;
            for (Order x : orders)
                if (identifier.equals(x.identifier))
                    order = x;
            if (order == null){
                System.out.print("\n\nPedido não encontrado!\n\n");
                return;
            }
            System.out.print("Digite o pedido: ");
            order.description = in.readLine();
            order.closingTime = Calendar.getInstance();
            order.priority = order.closingTime.getTimeInMillis();
            orders.remove(order);

            ServiceBox serviceBox = order.isPriority ? priorityServiceBoxes[random.nextInt(Util.size(priorityServiceBoxes))] : serviceBoxes[random.nextInt(Util.size(serviceBoxes))];
            serviceBox.leftistHeap = LeftistHeap.union(serviceBox.leftistHeap, new Heap(order));
            System.out.print("\n\nPedido cadastrado! Espere ser chamado pelo caixa.\n\n");
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }
}

package service;

import entity.Kitchen;
import entity.ServiceBox;
import service.heaps.LeftistHeap;
import service.heaps.SimpleHeap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class EmployeeArea {
    public static void menu(ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes, Kitchen kitchen){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int op = -1;
        try {
            while (op != 4){
                System.out.print("\n\n\n\n\t\t\tLANCHONETE\n\n");
                Util.printServiceBoxes(serviceBoxes, priorityServiceBoxes);
                Util.printOrders(kitchen);
                System.out.print("\n\n\t\t\tMENU DE OPÇÕES\n\n");
                System.out.print("\t1 - Atendimento de um cliente\n \t2 - Abertura/fechamento de um caixa\n \t3 - Conclusão de um pedido na cozinha\n \t4 - Voltar\n \t0 - Fechar lanchonete\n");
                System.out.print("\nDigite uma opção: ");
                op = Integer.parseInt(in.readLine());
                switch (op){
                    case 1 -> custumerService(serviceBoxes, priorityServiceBoxes, kitchen);
                    case 2 -> openingOrClosingBox(serviceBoxes, priorityServiceBoxes);
                    case 3 -> SimpleHeap.remove(kitchen);
                    case 4 -> {}
                    case 0 -> System.exit(0);
                    default -> System.out.println("Opção inválida");
                }
            }
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }

    private static void custumerService(ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes, Kitchen kitchen){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nDigite o número do caixa: ");
            String number = in.readLine();
            ServiceBox box = null;
            for (ServiceBox x : serviceBoxes) {
                String[] split = x.name.split(" - ");
                if (number.equals(split[1]))
                    box = x;
            }
            for (ServiceBox x : priorityServiceBoxes) {
                String[] split = x.name.split(" - ");
                if (number.equals(split[1]))
                    box = x;
            }
            if (box.leftistHeap == null){
                System.out.print("\n\nCaixa vazio!\n\n");
                return;
            }
            System.out.print("Fazer pagamento com cartão ou dinheiro ? ");
            String payment = in.readLine();
            System.out.print("\nPagamento realizado!\n");
            box.leftistHeap.order.priority = kitchen.tail + 2;
            SimpleHeap.add(kitchen, box.leftistHeap.order);
            box.leftistHeap = LeftistHeap.removal(box.leftistHeap);
            System.out.print("\nSeu pedido já foi para a cozinha!\n");
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }

    private static void openingOrClosingBox(ServiceBox[] serviceBoxes, ServiceBox[] priorityServiceBoxes){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("\nVocê quer abrir ou fechar um caixa? \nDigite 0 para abrir ou 1 para fechar\n");
            int option = Integer.parseInt(in.readLine());
            System.out.print("O caixa é prioritário? \nDigite true ou false\n");
            boolean isPriority = Boolean.parseBoolean(in.readLine());

            if (option == 0) {
                if (isPriority){
                    Optional<ServiceBox> priorityServiceBox = Arrays.stream(priorityServiceBoxes).filter(x -> !x.isOpen).findFirst();
                    if (priorityServiceBox.isEmpty()){
                        System.out.print("\n\nTodos os caixas já estão abertos!\n\n");
                        return;
                    }
                    priorityServiceBox.get().isOpen = true;
                }else {
                    Optional<ServiceBox> serviceBox = Arrays.stream(serviceBoxes).filter(x -> !x.isOpen).findFirst();
                    if (serviceBox.isEmpty()){
                        System.out.print("\n\nTodos os caixas já estão abertos!\n\n");
                        return;
                    }
                    serviceBox.get().isOpen = true;
                }
            }else {
                if (isPriority){
                    if (Util.size(priorityServiceBoxes) == 1){
                        System.out.print("\n\nPelo menos um caixa deve ficar aberto!\n\n");
                        return;
                    }
                    Optional<ServiceBox> closingPriorityServiceBox = Arrays.stream(priorityServiceBoxes).filter(x -> x.isOpen).findFirst();
                    closingPriorityServiceBox.get().isOpen = false;
                    Optional<ServiceBox> priorityServiceBox = Arrays.stream(priorityServiceBoxes).filter(x -> x.isOpen).findFirst();
                    priorityServiceBox.get().leftistHeap = LeftistHeap.union(closingPriorityServiceBox.get().leftistHeap, priorityServiceBox.get().leftistHeap);
                    closingPriorityServiceBox.get().leftistHeap = null;
                }else {
                    if (Util.size(serviceBoxes) == 1){
                        System.out.print("\n\nPelo menos um caixa deve ficar aberto!\n\n");
                        return;
                    }
                    Optional<ServiceBox> closingServiceBox = Arrays.stream(serviceBoxes).filter(x -> x.isOpen).findFirst();
                    closingServiceBox.get().isOpen = false;
                    Optional<ServiceBox> serviceBox = Arrays.stream(serviceBoxes).filter(x -> x.isOpen).findFirst();
                    serviceBox.get().leftistHeap = LeftistHeap.union(closingServiceBox.get().leftistHeap, serviceBox.get().leftistHeap);
                    closingServiceBox.get().leftistHeap = null;
                }
            }
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
        }
    }
}

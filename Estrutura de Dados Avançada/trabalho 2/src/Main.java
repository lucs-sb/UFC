import entity.Kitchen;
import entity.Order;
import entity.ServiceBox;
import service.EmployeeArea;
import service.UserArea;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int op = -1;
        List<Order> orders = new ArrayList<>();
        ServiceBox[] serviceBoxes = {new ServiceBox("Caixa - 3", false, true), new ServiceBox("Caixa - 4", false, true), new ServiceBox("Caixa - 5", false, false), new ServiceBox("Caixa - 6", false, false)};
        ServiceBox[] priorityServiceBoxes = {new ServiceBox("Caixa Prioritário - 1", true, true), new ServiceBox("Caixa Prioritário - 2", true, false)};
        Kitchen kitchen = new Kitchen(10);

        while (op != 0){
            System.out.print("\n\n\n\n\t\tLANCHONETE ABERTA - BEM-VINDO");
            System.out.print("\n\n\t\t\tMENU DE OPÇÕES\n\n");
            System.out.print("\t1 - Somente funcionários\n \t2 - Clientes\n");
            System.out.print("\nDigite uma opção: ");
            op = in.nextInt();
            switch (op) {
                case 1 -> EmployeeArea.menu(serviceBoxes, priorityServiceBoxes, kitchen);
                case 2 -> UserArea.menu(orders, serviceBoxes, priorityServiceBoxes, kitchen);
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
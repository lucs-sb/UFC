package service.avl;

import entity.Purchase;

import java.util.Random;

public class AVL {
    public static void Print(Purchase purchase){
        if (purchase == null)
            return;

        System.out.println("CPF: " + purchase.getSocialSecurity());
        System.out.println("Valor: " + purchase.getValue());
        System.out.println("Descrição: " + purchase.getDescription());

        if (purchase.getLeft() != null) {
            System.out.println("CPF: " + purchase.getSocialSecurity());
            System.out.println("Valor: " + purchase.getValue());
            System.out.println("Descrição: " + purchase.getDescription());
        }

        if (purchase.getRight() != null) {
            System.out.println("CPF: " + purchase.getSocialSecurity());
            System.out.println("Valor: " + purchase.getValue());
            System.out.println("Descrição: " + purchase.getDescription());
        }

        System.out.println();

        Print(purchase.getLeft());
        Print(purchase.getRight());
    }

    public static int HeightAVL(Purchase purchase){
        int heightLeft = 0, heightRight = 0;

        if (purchase == null)
            return 0;
        else {
            heightLeft = HeightAVL(purchase.getLeft());
            heightRight = HeightAVL(purchase.getRight());

            if (heightLeft > heightRight)
                return 1 + heightLeft;
            else
                return 1 + heightRight;
        }
    }

    public static int calculateBF(Purchase purchase){
        return HeightAVL(purchase.getLeft()) - HeightAVL(purchase.getRight());
    }

    public static Purchase LeftSingleRotation(Purchase purchase){
        Purchase aux = new Purchase(
                purchase.getRight().getDescription(),
                purchase.getRight().getValue(),
                purchase.getRight().getSocialSecurity(),
                purchase.getRight().getLeft(),
                purchase.getRight().getRight()
        );

        purchase.setRight(new Purchase(
                aux.getLeft().getDescription(),
                aux.getLeft().getValue(),
                aux.getLeft().getSocialSecurity(),
                aux.getLeft().getLeft(),
                aux.getLeft().getRight()
        ));

        aux.setLeft(new Purchase(
                purchase.getDescription(),
                purchase.getValue(),
                purchase.getSocialSecurity(),
                purchase.getLeft(),
                purchase.getRight()
        ));

        purchase.setDescription(aux.getDescription());
        purchase.setValue(aux.getValue());
        purchase.setSocialSecurity(aux.getSocialSecurity());
        purchase.setLeft(aux.getLeft());
        purchase.setRight(aux.getRight());

        return purchase;
    }

    public static Purchase RightSingleRotation(Purchase purchase){
        Purchase aux = new Purchase(
                purchase.getLeft().getDescription(),
                purchase.getLeft().getValue(),
                purchase.getLeft().getSocialSecurity(),
                purchase.getLeft().getLeft(),
                purchase.getLeft().getRight()
        );
//
//        purchase.setLeft(new Purchase(
//                aux.getRight().getDescription(),
//                aux.getRight().getValue(),
//                aux.getRight().getSocialSecurity(),
//                aux.getRight().getLeft(),
//                aux.getRight().getRight()
//        ));
//
//        aux.setRight(new Purchase(
//                purchase.getDescription(),
//                purchase.getValue(),
//                purchase.getSocialSecurity(),
//                purchase.getLeft(),
//                purchase.getRight()
//        ));
        purchase.setLeft(aux.getRight());
        purchase.setRight(purchase);

        purchase.setDescription(aux.getDescription());
        purchase.setValue(aux.getValue());
        purchase.setSocialSecurity(aux.getSocialSecurity());
        purchase.setLeft(aux.getLeft());
        purchase.setRight(aux.getRight());

        return purchase;
    }

    public static Purchase Balancing(Purchase purchase){
        int factor = calculateBF(purchase);

        if (factor > 1)
            return LeftSingleRotation(purchase);
        else if (factor < -1)
            return RightBalance(purchase);

        return purchase;
    }

    public static Purchase RightBalance(Purchase purchase){
        int factor = calculateBF(purchase.getRight());

        if (factor < 0)
            return LeftSingleRotation(purchase);
        else if (factor > 0) {
            purchase.setRight(RightSingleRotation(purchase.getRight()));
            return LeftSingleRotation(purchase);
        }
        else
            return purchase;
    }

    public static Purchase LeftBalance(Purchase purchase){
        int factor = calculateBF(purchase.getLeft());

        if (factor > 0)
            return RightSingleRotation(purchase);
        else if (factor < 0) {
            purchase.setLeft(LeftSingleRotation(purchase.getLeft()));
            return RightSingleRotation(purchase);
        }
        else
            return purchase;
    }

    public static Purchase AddPurchase(Purchase purchase, Purchase newPurchase){
        if (purchase == null)
            purchase = new Purchase(
                    newPurchase.getDescription(),
                    newPurchase.getValue(),
                    newPurchase.getSocialSecurity(),
                    null,
                    null
            );
        else
            if (purchase.getId() == newPurchase.getId()){
                Random random = new Random();
                newPurchase.setId(random.nextInt(1000));

                AddPurchase(purchase, newPurchase);
            }
            else if (purchase.getId() > newPurchase  .getId()){
                purchase.setLeft(AddPurchase(purchase.getLeft(), newPurchase));
                purchase = Balancing(purchase);
            }
            else {
                purchase.setRight(AddPurchase(purchase.getRight(), newPurchase));
                purchase = Balancing(purchase);
            }

        return purchase;
    }
}

package service.avl;

import entity.Purchase;

import java.util.Random;

public class PurchaseAVL {
    public static void print(Purchase purchase){
        if (purchase == null)
            return;

        System.out.println("\nIdentificador da compra: " + purchase.getId());
        System.out.println("CPF: " + purchase.getSocialSecurity());
        System.out.println("Valor: " + purchase.getValue());
        System.out.println("Descrição: " + purchase.getDescription());

        print(purchase.getLeft());
        print(purchase.getRight());
    }

    private static int heightAVL(Purchase purchase){
        int heightLeft = 0, heightRight = 0;

        if (purchase == null)
            return 0;
        else {
            heightLeft = heightAVL(purchase.getLeft());
            heightRight = heightAVL(purchase.getRight());

            if (heightLeft > heightRight)
                return 1 + heightLeft;
            else
                return 1 + heightRight;
        }
    }

    private static int calculateBF(Purchase purchase){
        return heightAVL(purchase.getLeft()) - heightAVL(purchase.getRight());
    }

    private static Purchase leftSingleRotation(Purchase purchase){
        Purchase aux = purchase.getRight();
        purchase.setRight(aux.getLeft());
        aux.setLeft(purchase);
        purchase = aux;
        return purchase;
    }

    private static Purchase rightSingleRotation(Purchase purchase){
        Purchase aux = purchase.getLeft();
        purchase.setLeft(aux.getRight());
        aux.setRight(purchase);
        purchase = aux;
        return purchase;
    }

    private static Purchase balancing(Purchase purchase){
        int factor = calculateBF(purchase);

        if (factor > 1)
            return leftBalance(purchase);
        else if (factor < -1)
            return rightBalance(purchase);

        return purchase;
    }

    private static Purchase rightBalance(Purchase purchase){
        int factor = calculateBF(purchase.getRight());

        if (factor < 0)
            return leftSingleRotation(purchase);
        else if (factor > 0) {
            purchase.setRight(rightSingleRotation(purchase.getRight()));
            return leftSingleRotation(purchase);
        }
        else
            return purchase;
    }

    private static Purchase leftBalance(Purchase purchase){
        int factor = calculateBF(purchase.getLeft());

        if (factor > 0)
            return rightSingleRotation(purchase);
        else if (factor < 0) {
            purchase.setLeft(leftSingleRotation(purchase.getLeft()));
            return rightSingleRotation(purchase);
        }
        else
            return purchase;
    }

    public static Purchase addPurchase(Purchase purchase, Purchase newPurchase){
        if (purchase == null)
            purchase = newPurchase;
        else
            if (purchase.getId() == newPurchase.getId()){
                Random random = new Random();
                newPurchase.setId(random.nextInt(1000));

                addPurchase(purchase, newPurchase);
            }
            else if (purchase.getId() > newPurchase  .getId()){
                purchase.setLeft(addPurchase(purchase.getLeft(), newPurchase));
                purchase = balancing(purchase);
            }
            else {
                purchase.setRight(addPurchase(purchase.getRight(), newPurchase));
                purchase = balancing(purchase);
            }

        return purchase;
    }
}

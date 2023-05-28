package service.hash;

import entity.CreditCard;
import entity.User;

public class CardHash extends HashTable {
    public CreditCard[] list;

    public CardHash(int max) {
        super(max);
        list = new CreditCard[this.max];
    }

    public void print(){
        if (this.size == 0)
            System.out.println("\nNão existe cartões cadastrados.");
        else
        {
            for (int i = 0; i < this.size -1; i++) {
                System.out.println("\nNúmero do cartão: " + list[i].getNumber());
                System.out.println("Titular: " + list[i].getCardHolder());
                System.out.println("Data de vencimento: " + list[i].getExpirationDate() + ", Bandeira: " + list[i].getFlag());
                System.out.print("CPFs vinculados: ");
                list[i].getSocialSecurities().forEach(socialSecurity -> {
                    System.out.print(socialSecurity + "; ");
                });
            }
        }
    }

    public void addCreditCard(CreditCard newCreditCard){
        int index = hashing(newCreditCard.getNumber());

        while (list[index] != null)
            index = doubleHash(index);

        list[index] = newCreditCard;

        this.size++;

        if (calculateOccupancyRate())
            expandTable();
    }

    public CreditCard getCreditCardByNumber(long number, User user, boolean isAdd){
        int index = hashing(number);

        while (list[index] != null && list[index].getNumber() != number)
            index = doubleHash(index);

        if (!isAdd)
            return list[index] == null || !user.getCards().contains(number) ? null : list[index];
        else
            return list[index] == null ? null : list[index];
    }

    public CreditCard getCreditCardByNumber(long number){
        int index = hashing(number);

        while (list[index] != null && list[index].getNumber() != number)
            index = doubleHash(index);

        return list[index] == null ? null : list[index];
    }

    private void expandTable(){
        CardHash aux = new CardHash(2 * max);
        for (int i = 0; i < max; i++)
            if (list[i] != null){
                int index = aux.hashing(list[i].getNumber());

                while (aux.list[index] != null)
                    index = aux.doubleHash(index);

                aux.list[index] = list[i];
            }

        list = aux.list;
        max = 2 * max;
    }
}

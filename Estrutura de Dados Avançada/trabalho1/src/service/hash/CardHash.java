package service.hash;

import entity.CreditCard;

public class CardHash extends TList{
    public CreditCard List[] = new CreditCard[MAX];

    public CardHash(int MAX) {
        super(MAX);
    }

    public int Hashing(int chave){ return chave % this.MAX; }

    public int DoubleHash(int i){
        int w = 1 + (i % (this.MAX - 1));
        return (i + w) % this.MAX;
    }

    public void Print(){
        if (this.Size == 0)
            System.out.println("Não existe cartões cadastrados.");
        else
        {
            for (int i = 0; i < this.Size-1; i++) {
                System.out.println("Número do cartão: " + List[i].getNumber());
                System.out.println("Titular: " + List[i].getCardHolder());
                System.out.println("Data de vencimento: " + List[i].getExpirationDate() + ", Bandeira: " + List[i].getFlag());
                System.out.println("CPFs vinculados: : ");
                List[i].getSocialSecurities().forEach(socialSecurity -> {
                    System.out.print(socialSecurity + " ");
                });
            }
        }
    }

    public void AddCreditCard(CreditCard newCreditCard, int socialSecurity){
        int index = Hashing(newCreditCard.getNumber());

        while (List[index] != null)
            index = DoubleHash(index);

        List[index].setNumber(newCreditCard.getNumber());
        List[index].setCardHolder(newCreditCard.getCardHolder());
        List[index].setExpirationDate(newCreditCard.getExpirationDate());
        List[index].setFlag(newCreditCard.getFlag());
        List[index].getSocialSecurities().add(socialSecurity);
    }
}

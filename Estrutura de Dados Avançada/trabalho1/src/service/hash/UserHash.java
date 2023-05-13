package service.hash;

import entity.User;

public class UserHash extends TList{
    public User List[] = new User[MAX];

    public UserHash(int MAX) {
        super(MAX);
    }

    public int Hashing(int chave){ return chave % this.MAX; }

    public int DoubleHash(int i){
        int w = 1 + (i % (this.MAX - 1));
        return (i + w) % this.MAX;
    }

    public void Print(){
        if (this.Size == 0)
            System.out.println("Não existe usuários cadastrados.");
        else
        {
            for (int i = 0; i < this.Size-1; i++) {
                System.out.println("Nome: " + List[i].getName());
                System.out.println("CPF: " + List[i].getSocialSecurity());
                System.out.println("Endereço: " + List[i].getAddress());
                System.out.println("Cartões de crédito vinculados: : ");
                List[i].getCards().forEach(card -> {
                    System.out.print(card + " ");
                });
            }
        }
    }

    public void AddUser(User newUser, int creditCardNumber){
        int index = Hashing(newUser.getSocialSecurity());

        while (List[index] != null)
            index = DoubleHash(index);

        List[index].setName(newUser.getName());
        List[index].setSocialSecurity(newUser.getSocialSecurity());
        List[index].setAddress(newUser.getAddress());
        List[index].getCards().add(creditCardNumber);
    }
}

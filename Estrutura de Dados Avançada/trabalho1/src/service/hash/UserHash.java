package service.hash;

import entity.User;

public class UserHash extends HashTable {
    public User[] list;

    public UserHash(int max) {
        super(max);
        list = new User[this.max];
    }

    public void print(){
        if (this.size == 0)
            System.out.println("\nNão existe usuários cadastrados.");
        else
        {
            for (int i = 0; i < this.size -1; i++) {
                System.out.println("\nNome: " + list[i].getName());
                System.out.println("CPF: " + list[i].getSocialSecurity());
                System.out.println("Endereço: " + list[i].getAddress());
                System.out.print("Cartões de crédito vinculados: ");
                list[i].getCards().forEach(card -> {
                    System.out.print(card + "; ");
                });
            }
        }
    }

    public User addUser(User newUser){
        int index = hashing(newUser.getSocialSecurity());

        while (list[index] != null)
            index = doubleHash(index);

        list[index] = newUser;

        this.size++;

        if (calculateOccupancyRate())
            expandTable();

        return list[index];
    }

    public User getUserBySocialSecurity(long socialSecurity){
        int index = hashing(socialSecurity);

        while (list[index] != null && list[index].getSocialSecurity() != socialSecurity)
            index = doubleHash(index);

        return list[index] == null ? null : list[index];
    }

    private void expandTable(){
        UserHash aux = new UserHash(2 * max);
        for (int i = 0; i < max; i++)
            if (list[i] != null){
                int index = aux.hashing(list[i].getSocialSecurity());

                while (aux.list[index] != null)
                    index = aux.doubleHash(index);

                aux.list[index] = list[i];
            }

        list = aux.list;
        max = 2 * max;
    }
}

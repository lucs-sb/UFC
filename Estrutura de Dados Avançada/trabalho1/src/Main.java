import entity.CreditCard;
import entity.Purchase;
import entity.User;
import service.avl.PurchaseAVL;
import service.hash.CardHash;
import service.hash.UserHash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserHash userHashTable = new UserHash(5);
        CardHash cardHashTable = new CardHash(5);
        User user;
        Scanner s = new Scanner(System.in);
        int op;

        List<Long> numbers = new ArrayList<>();
        numbers.add(1234L);
        User user1 = new User("Lucas", 52142384574L, "Centro", numbers);
        List<Long> cpfs = new ArrayList<>();
        cpfs.add(52142384574L);
        CreditCard card1 = new CreditCard(1234L, "Lucas", "05/25", "Visa", cpfs, new Purchase("Roupa", 350, 52142384574L, null, null));
        cardHashTable.addCreditCard(card1);
        userHashTable.addUser(user1);

        numbers = new ArrayList<>();
        numbers.add(9874L);
        User user2 = new User("Aguiar", 56895684521L, "Centro", numbers);
        cpfs = new ArrayList<>();
        cpfs.add(56895684521L);
        CreditCard card2 = new CreditCard(9874L, "Aguiar", "05/25", "Visa", cpfs, new Purchase("Merenda", 50, 56895684521L, null, null));
        cardHashTable.addCreditCard(card2);
        userHashTable.addUser(user2);

        numbers = new ArrayList<>();
        numbers.add(5685L);
        User user3 = new User("Vitim", 45212365287L, "Centro", numbers);
        cpfs = new ArrayList<>();
        cpfs.add(45212365287L);
        CreditCard card3 = new CreditCard(5685L, "Vitim", "05/25", "Visa", cpfs, new Purchase("Mercado", 100, 45212365287L, null, null));
        cardHashTable.addCreditCard(card3);
        userHashTable.addUser(user3);

        do{
            System.out.println("\n\t\t\t\tGERENCIADOR DE CARTÕES\n");
            System.out.println("\t1  - FAZER LOGIN\n\t2  - CRIAR USUÁRIO\n\t0  - SAIR");
            System.out.print("\nINFORME SUA OPCAO: ");
            op = s.nextInt();

            switch(op) {
                case 1:
                    user = Login(userHashTable);
                    if (user != null)
                        MenuUser(user, cardHashTable);
                    break;
                case 2:
                    user = CreateUser(userHashTable, cardHashTable);
                    if (user != null)
                        MenuUser(user, cardHashTable);
                    break;
                case 0:
                    System.out.println("\nENCERRANDO PROGRAMA");
                    break;
                default:
                    System.out.println("\nOPCAO INCORRETA, TENTAR NOVAMENTE.");
                    break;
            }
        }
        while(op != 0);
    }

    private static User Login(UserHash userHash){
        Scanner s = new Scanner(System.in);

        System.out.println("\n\t\t\t\tGERENCIADOR DE CARTÕES\n");
        System.out.println("\t\t\t\t\t\tLOGIN");
        System.out.print("\nINFORME SEU CPF: ");
        long socialSecurity = s.nextLong();

        User user = userHash.getUserBySocialSecurity(socialSecurity);

        if (user == null)
            System.out.println("\nCPF NÃO CADASTRADO! APERTE 2 PARA CADASTRAR-SE.");

        return user;
    }

    private static User CreateUser(UserHash userHash, CardHash cardHash){
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        User user = new User();
        CreditCard creditCard;

        try {
            System.out.println("\n\t\t\t\tGERENCIADOR DE CARTÕES\n");
            System.out.println("\t\t\t\t\tCRIAR USUÁRIO");
            System.out.print("\nINFORME SEU NOME: ");
            user.setName(input.readLine());
            System.out.print("INFORME SEU CPF: ");
            user.setSocialSecurity(Long.parseLong(input.readLine()));
            System.out.print("INFORME ENDEREÇO: ");
            user.setAddress(input.readLine());
            System.out.print("INFORME O NÚMERO DO SEU CARTÃO: ");
            long cardNumber = Long.parseLong(input.readLine());

            creditCard = cardHash.getCreditCardByNumber(cardNumber);

            if (creditCard == null) {
                List<Long> socialSecurities = new ArrayList<>();
                socialSecurities.add(user.getSocialSecurity());

                creditCard = new CreditCard(cardNumber, socialSecurities);

                System.out.print("INFORME O TITULAR DO SEU CARTÃO: ");
                creditCard.setCardHolder(input.readLine());
                System.out.print("INFORME A VALIDADE DO SEU CARTÃO: ");
                creditCard.setExpirationDate(input.readLine());
                System.out.print("INFORME A BANDEIRA DO SEU CARTÃO: ");
                creditCard.setFlag(input.readLine());

                cardHash.addCreditCard(creditCard);
            }
        }catch (IOException e){
            e.fillInStackTrace();
            System.out.print("ALGO INESPERADO ACONTECEU");
            return null;
        }

        List<Long> cardNumbers = new ArrayList<>();
        cardNumbers.add(creditCard.getNumber());

        user.setCards(cardNumbers);

        user = userHash.addUser(user);

        System.out.println("\nUSUÁRIO CRIADO COM SUCESSO!\n");

        return user;
    }

    private static void MenuUser(User user, CardHash cardHash){
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        CreditCard card;
        int op = 6;
        long cardNumber;

        do{
            System.out.println("\n\t\t\t\tGERENCIADOR DE CARTÕES\n");
            System.out.println("\t1  - CADASTRAR COMPRA\n\t2  - LISTAR COMPRAS\n\t3  - LISTAR MEUS CARTÕES");
            System.out.println("\t4  - ADICIONAR NOVO CARTÃO\n\t5  - LISTAR MEUS DADOS\n\t0  - SAIR");
            System.out.print("\nINFORME SUA OPCAO: ");
            try {

                op = Integer.parseInt(input.readLine());

                switch (op) {
                    case 1:
                        System.out.print("\nINFORME O NÚMERO DO CARTÃO: ");
                        cardNumber = Long.parseLong(input.readLine());

                        card = cardHash.getCreditCardByNumber(cardNumber, user, false);

                        if (card == null)
                            System.out.print("\nCARTÃO NÃO ENCONTRADO.\n");
                        else {
                            System.out.print("INFORME A DESCRIÇÃO: ");
                            String description = input.readLine();
                            System.out.print("INFORME O VALOR DA COMPRA: ");
                            double value = Double.parseDouble(input.readLine());

                            Purchase purchase = new Purchase(description, value, user.getSocialSecurity(), null, null);

                            if (card.getPurchase() == null)
                                card.setPurchase(PurchaseAVL.addPurchase(null, purchase));
                            else
                                PurchaseAVL.addPurchase(card.getPurchase(), purchase);

                            System.out.println("\nCOMPRA CADASTRADA!\n");
                        }
                        break;
                    case 2:
                        System.out.print("\nINFORME O NÚMERO DO CARTÃO: ");
                        cardNumber = Long.parseLong(input.readLine());

                        card = cardHash.getCreditCardByNumber(cardNumber, user, false);

                        if (card == null)
                            System.out.println("\nCARTÃO NÃO ENCONTRADO.");
                        else if (card.getPurchase() == null)
                            System.out.println("\nNENHUMA COMPRA FOI CADASTRADA NESSE CARTÃO.");
                        else
                            PurchaseAVL.print(card.getPurchase());

                        break;
                    case 3:
                        user.getCards().forEach(number -> {
                            System.out.println("\nNÚMERO DO CARTÃO: " + number);
                        });

                        break;
                    case 4:
                        System.out.print("\nINFORME O NÚMERO DO SEU CARTÃO: ");
                        cardNumber = Long.parseLong(input.readLine());

                        CreditCard creditCard = cardHash.getCreditCardByNumber(cardNumber, user, true);

                        if (creditCard != null) {
                            if (user.getCards().contains(cardNumber))
                                System.out.println("\nCARTÃO JÁ CADASTRADO.\n");
                            else
                                user.getCards().add(creditCard.getNumber());

                            break;
                        }

                        List<Long> socialSecurities = new ArrayList<>();
                        socialSecurities.add(user.getSocialSecurity());

                        creditCard = new CreditCard(cardNumber, socialSecurities);
                        System.out.print("INFORME O TITULAR DO SEU CARTÃO: ");
                        creditCard.setCardHolder(input.readLine());
                        System.out.print("INFORME A VALIDADE DO SEU CARTÃO: ");
                        creditCard.setExpirationDate(input.readLine());
                        System.out.print("INFORME A BANDEIRA DO SEU CARTÃO: ");
                        creditCard.setFlag(input.readLine());

                        user.getCards().add(creditCard.getNumber());

                        cardHash.addCreditCard(creditCard);

                        System.out.println("\nCARTÃO CADASTRADO COM SUCESSO!\n");
                        break;
                    case 5:
                        System.out.println("\nNOME: " + user.getName());
                        System.out.println("CPF: " + user.getSocialSecurity());
                        System.out.println("ENDEREÇO: " + user.getAddress());
                        break;
                    case 0:
                        System.out.println("\nVOLTANDO PARA MENU INICIAL");
                        break;
                    default:
                        System.out.println("\nOPÇÃO INCORRETA, TENTAR NOVAMENTE.");
                        break;
                }
            }catch (IOException e){
                e.fillInStackTrace();
                System.out.print("ALGO INESPERADO ACONTECEU");
            }
        }
        while(op != 0);
    }
}
package entity;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class CreditCard {
    private int Number;
    private String CardHolder;
    private LocalDateTime ExpirationDate;
    private String Flag;
    private List<Integer> SocialSecurities = new ArrayList<>();
    private Purchase Purchase;

    public CreditCard(int number, String cardHolder, LocalDateTime expirationDate, String flag) {
        Number = number;
        CardHolder = cardHolder;
        ExpirationDate = expirationDate;
        Flag = flag;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public LocalDateTime getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public List<Integer> getSocialSecurities() {
        return SocialSecurities;
    }

    public void setSocialSecurities(List<Integer> socialSecurities) {
        SocialSecurities = socialSecurities;
    }

    public Purchase getPurchase() {
        return Purchase;
    }

    public void setPurchase(Purchase purchase) {
        Purchase = purchase;
    }
}

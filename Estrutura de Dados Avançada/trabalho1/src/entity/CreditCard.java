package entity;

import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private long number;
    private String cardHolder;
    private String expirationDate;
    private String flag;
    private List<Long> socialSecurities;
    private Purchase purchase;

    public CreditCard(long number, List<Long> socialSecurities) {
        this.number = number;
        this.socialSecurities = socialSecurities;
        this.purchase = null;
    }

    public long getNumber() {
        return this.number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCardHolder() {
        return this.cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Long> getSocialSecurities() {
        return this.socialSecurities;
    }

    public void setSocialSecurities(List<Long> socialSecurities) {
        this.socialSecurities = socialSecurities;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}

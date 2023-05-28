package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private long socialSecurity;
    private String address;
    private List<Long> cards;

    public User(){}

    public User(String name, long socialSecurity, String address, List<Long> cards) {
        this.name = name;
        this.socialSecurity = socialSecurity;
        this.address = address;
        this.cards = cards;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSocialSecurity() {
        return this.socialSecurity;
    }

    public void setSocialSecurity(long socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getCards() {
        return this.cards;
    }

    public void setCards(List<Long> cards) {
        this.cards = cards;
    }
}

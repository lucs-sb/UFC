package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String Name;
    private int SocialSecurity;
    private String Address;
    private List<Integer> Cards = new ArrayList<>();

    public User(String name, int socialSecurity, String address) {
        Name = name;
        SocialSecurity = socialSecurity;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSocialSecurity() {
        return SocialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        SocialSecurity = socialSecurity;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<Integer> getCards() {
        return Cards;
    }
}

package entity;

import java.util.Random;

public class Purchase {
    private int Id;
    private String Description;
    private double Value;
    private int SocialSecurity;
    private Purchase Left;
    private Purchase Right;

    public Purchase(String description, double value, int socialSecurity) {
        Description = description;
        Value = value;
        SocialSecurity = socialSecurity;
    }

    public Purchase(String description, double value, int socialSecurity, Purchase left, Purchase right) {
        Random random = new Random();
        Id = random.nextInt(1000);
        Description = description;
        Value = value;
        SocialSecurity = socialSecurity;
        Left = left;
        Right = right;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public int getSocialSecurity() {
        return SocialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        SocialSecurity = socialSecurity;
    }

    public Purchase getLeft() {
        return Left;
    }

    public void setLeft(Purchase left) {
        this.Left = left;
    }

    public Purchase getRight() {
        return Right;
    }

    public void setRight(Purchase right) {
        this.Right = right;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

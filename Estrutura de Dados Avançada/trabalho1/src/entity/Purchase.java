package entity;

import java.util.Random;

public class Purchase {
    private long id;
    private String description;
    private double value;
    private long socialSecurity;
    private Purchase left;
    private Purchase right;

    public Purchase(String description, double value, long socialSecurity, Purchase left, Purchase right) {
        Random random = new Random();
        this.id = random.nextInt(1000);
        this.description = description;
        this.value = value;
        this.socialSecurity = socialSecurity;
        this.left = left;
        this.right = right;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getSocialSecurity() {
        return this.socialSecurity;
    }

    public void setSocialSecurity(long socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Purchase getLeft() {
        return this.left;
    }

    public void setLeft(Purchase left) {
        this.left = left;
    }

    public Purchase getRight() {
        return this.right;
    }

    public void setRight(Purchase right) {
        this.right = right;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

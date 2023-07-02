package entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Order {
    public String clientName;
    public String identifier;
    public boolean isPriority;
    public Calendar orderTime;
    public Calendar closingTime;
    public long priority;
    public String description;

    public Order(String clientName, boolean isPriority, Calendar orderTime) {
        this.clientName = clientName;
        this.identifier = UUID.randomUUID().toString().substring(0,6);
        this.isPriority = isPriority;
        this.orderTime = orderTime;
    }
}

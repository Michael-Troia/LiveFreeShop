package com.example.livefree.Models;
import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String item;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private long cost;

    public Order(){}

    public Order(User user, long quantity, long cost, String item){
        this.user = user;
        this.quantity = quantity;
        this.cost = cost;
        this.item = item;
    }

    public Order(long id, User user, long quantity, long cost, String item){
        this.id = id;
        this.user = user;
        this.quantity = quantity;
        this.cost = cost;
        this.item = item;
    }

    public User getUser(){ return user; }
    public long getId(){ return id; }
    public long getQuantity(){ return quantity; }
    public long getCost(){ return cost; }
    public String getItem(){ return item; }

    public void setId(long id){ this.id = id; }
    public void setUser(User user){ this.user = user; }
    public void setQuantity(long quantity){ this.quantity = quantity; }
    public void setCost(long cost){ this.cost = cost; }
    public void setItem(String item){ this.item = item; }
}
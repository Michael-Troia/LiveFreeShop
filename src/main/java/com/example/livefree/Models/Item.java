package com.example.livefree.Models;
import javax.persistence.*;

@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long quantity;

    public Item(){}

    public Item(String name, long quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public Item(long id, String name, long quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){ return name; }
    public long getId(){ return id; }
    public long getQuantity(){ return quantity; }

    public void setId(long id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setQuantity(long quantity){ this.quantity = quantity; }
}
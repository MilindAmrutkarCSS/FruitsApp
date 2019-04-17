package com.example.fruitsapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruit_table")
public class Fruit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int count;

    private int cartCount;

    private boolean inCart;

    public Fruit(String name, int count, int cartCount, boolean inCart) {
        this.name = name;
        this.count = count;
        this.cartCount = cartCount;
        this.inCart = inCart;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCartCount() {
        return cartCount;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
}

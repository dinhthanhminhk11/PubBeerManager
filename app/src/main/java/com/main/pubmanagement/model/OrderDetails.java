package com.main.pubmanagement.model;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    private int id;
    private int idOrder;
    private int quantity;
    private int discount;
    private int price;
    private String name;
    private int idMenu;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderDetails() {
    }

    public OrderDetails(int id, int idOrder, int quantity, int discount, int price, String name, int idMenu) {
        this.id = id;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
        this.name = name;
        this.idMenu = idMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

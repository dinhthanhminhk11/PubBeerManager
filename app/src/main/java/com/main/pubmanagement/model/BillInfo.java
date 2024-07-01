package com.main.pubmanagement.model;

public class BillInfo {
    private int id;
    private int idBill;
    private int idMenu;
    private int quantity;
    private int discount;

    public BillInfo() {
    }

    public BillInfo(int id, int idBill, int idMenu, int quantity, int discount) {
        this.id = id;
        this.idBill = idBill;
        this.idMenu = idMenu;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
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
}

package com.main.pubmanagement.model;

import java.io.Serializable;

public class Bill implements Serializable {
    private int id;
    private int type;
    private int idUser;
    private int idTable;
    private int price;
    private int priceDiscount;
    private String time;
    private int countPerson;
    private String nameTable;
    private String storeyName;
    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public Bill() {
    }

    public Bill(int id, int type, int idUser, int idTable,int price, int priceDiscount, String time,int countPerson) {
        this.id = id;
        this.type = type;
        this.idUser = idUser;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.time = time;
        this.countPerson =countPerson;
        this.idTable = idTable;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getCountPerson() {
        return countPerson;
    }

    public void setCountPerson(int countPerson) {
        this.countPerson = countPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }
}

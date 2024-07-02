package com.main.pubmanagement.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private int id;
    private String name;
    private int price;
    private int unit;
    private int idMenuType;
    private int discount;

    private String content;
    private int count;

    public String getContent() {
        return content;
    }

    public int getCount() {
        return count;

    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Menu(String name, int price, int unit, int idMenuType, int discount, String content, int count) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.idMenuType = idMenuType;
        this.discount = discount;
        this.content = content;
        this.count = count;
    }

    public Menu(int id, String name, int price, int unit, int idMenuType, int discount, String content, int count) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.idMenuType = idMenuType;
        this.discount = discount;
        this.content = content;
        this.count = count;
        this.id = id;
    }

    public Menu() {
    }


    public Menu(int id, String name, int price, int unit, int idMenuType, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.idMenuType = idMenuType;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    //*
    // quy định bao gồm có nhưng đơn vị sau
    // 0 tháp dành cho bia
    // 1 đĩa
    // 2 con
    // 3 nồi
    // 4 KG
    // 5 suất
    // 6 bát tô
    // 7 ly
    // 8 chai
    // 9 lon
    // */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getIdMenuType() {
        return idMenuType;
    }

    public void setIdMenuType(int idMenuType) {
        this.idMenuType = idMenuType;
    }
}

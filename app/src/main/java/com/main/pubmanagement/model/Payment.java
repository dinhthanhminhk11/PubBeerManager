package com.main.pubmanagement.model;

public class Payment {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Payment(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

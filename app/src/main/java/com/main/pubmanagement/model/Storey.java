package com.main.pubmanagement.model;

import java.io.Serializable;

public class Storey implements Serializable {
    private int id;
    private String name;
    private int countTable;


    public Storey() {
    }

    public Storey(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Storey(int id, String name, int countTable) {
        this.id = id;
        this.name = name;
        this.countTable = countTable;
    }

    public int getCountTable() {
        return countTable;
    }

    public void setCountTable(int countTable) {
        this.countTable = countTable;
    }

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
}

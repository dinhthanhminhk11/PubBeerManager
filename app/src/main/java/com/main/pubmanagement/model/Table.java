package com.main.pubmanagement.model;

import java.io.Serializable;

public class Table implements Serializable {
    private int id;
    private String name;
    private int countChair;
    private int status;
    private int idStorey;
    private String nameStorey;
    public Table() {
    }

    public Table(int id, String name, int countChair, int status, int idStorey,String nameStorey) {
        this.id = id;
        this.name = name;
        this.countChair = countChair;
        this.status = status;
        this.idStorey = idStorey;
        this.nameStorey = nameStorey;
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

    public int getCountChair() {
        return countChair;
    }

    public void setCountChair(int countChair) {
        this.countChair = countChair;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdStorey() {
        return idStorey;
    }

    public void setIdStorey(int idStorey) {
        this.idStorey = idStorey;
    }

    public String getNameStorey() {
        return nameStorey;
    }

    public void setNameStorey(String nameStorey) {
        this.nameStorey = nameStorey;
    }
}

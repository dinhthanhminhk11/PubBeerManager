package com.main.pubmanagement.model;

public class Order {
    private int id;
    private int idUser;
    private int idTable;
    private int status;
    private String createAt;
    private int total_price;
    private int person;
    public Order() {
    }

//    0 là chưa có người ngồi
//            2 là đã giao

    public Order(int id, int idUser, int idTable, int status, String createAt, int total_price , int person ) {
        this.id = id;
        this.idUser = idUser;
        this.idTable = idTable;
        this.status = status;// 0 ddang cho 1 // ddax bao bep // 2 da giao
        this.createAt = createAt;
        this.total_price = total_price;
        this.person = person;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}

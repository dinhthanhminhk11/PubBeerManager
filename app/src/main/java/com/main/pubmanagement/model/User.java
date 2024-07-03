package com.main.pubmanagement.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private int role;
    private int shift;

    private String phone;
    private String cardId;

    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public User() {
    }

    public User(int id, String name, int role, int shift) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.shift = shift;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String name, String phone, String cardId, String username, String password, int role, int shift, int salary) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.shift = shift;
        this.phone = phone;
        this.cardId = cardId;
        this.salary = salary;
    }

    public User(String name, String phone, String cardId, String username, String password, int role, int shift, int salary) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.shift = shift;
        this.phone = phone;
        this.cardId = cardId;
        this.salary = salary;
    }

    public User(int id, String name, String phone, String cardId, String username, String password) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.cardId = cardId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}

package com.main.pubmanagement.model;

import java.io.Serializable;

public class MenuType implements Serializable {
    private int id;
    private String name;

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

    public MenuType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

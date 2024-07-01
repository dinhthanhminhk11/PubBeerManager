package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.Storey;

import java.util.List;

public interface MenuDAO {
    void create(String name);

    void update(int id, String name);

    void create(int id);

    List<Menu> getListMenu();

    List<Menu> getListMenuByIdMenuType(int id);
}

package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.model.Storey;

import java.util.List;

public interface MenuTypeDAO {
    long create(MenuType menuType);

    void update(int id, String name);

    void create(int id);

    List<Storey> getListMenuType();
}

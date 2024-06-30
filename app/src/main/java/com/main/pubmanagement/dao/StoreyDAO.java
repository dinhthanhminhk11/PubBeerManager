package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Storey;

import java.util.List;

public interface StoreyDAO {
    void create(String name);

    void update(int id, String name);

    void create(int id);

    List<Storey> getListStorey();
}

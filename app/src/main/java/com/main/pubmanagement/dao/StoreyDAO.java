package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Storey;

import java.util.List;

public interface StoreyDAO {
    long create(String name);

    long update(int id, String name);

    void delete(int id);

    List<Storey> getListStorey();
}

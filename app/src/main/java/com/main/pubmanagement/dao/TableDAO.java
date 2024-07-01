package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;

import java.util.List;

public interface TableDAO {
    void create(String name);

    void update(int id, String name);

    void create(int id);

    List<Table> getListTable();

    List<Table> getListTableByIdStorey(int id);
    List<Table> getListTableByStatus(boolean status);
    List<Order> getOrdersByRestaurantId(int restaurantId);
}

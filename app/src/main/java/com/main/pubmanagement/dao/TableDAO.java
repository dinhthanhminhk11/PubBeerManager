package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;

import java.util.List;

public interface TableDAO {
    long create(String name, int countChair, int idStorey);

    long update(int id, String name, int countChair);

    void delete(int id);

    List<Table> getListTable();

    List<Table> getListTableByIdStorey(int id);

    List<Table> getListTableByStatus(boolean status);

    List<Order> getOrdersByRestaurantId(int restaurantId);
}

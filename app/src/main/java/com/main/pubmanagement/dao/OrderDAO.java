package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.OrderDetails;
import com.main.pubmanagement.model.Table;

import java.util.List;

public interface OrderDAO {
    long create(Order order);

    boolean createOrderDetail(OrderDetails order);

    Order getOrderById(long id);

    List<OrderDetails> getListOrderDetailsByIdOrder(long id);

    Table getTableById(long id);

    void removeOrderDetailByIdOrder(int orderId);
    void removeOrderById(int orderId);

    void updateStatusTable(int i);
}

package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.OrderDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.OrderDetails;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;

import java.util.ArrayList;
import java.util.List;

public class OderController implements OrderDAO {
    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public OderController(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    @Override
    public long create(Order order) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_USER_ID, order.getIdUser());
        contentValues.put(AppConstant.COLUMN_RESTAURANT_ID, order.getIdTable());
        contentValues.put(AppConstant.COLUMN_ORDER_STATUS, order.getStatus());
        contentValues.put(AppConstant.COLUMN_ORDER_CREATE_AT, order.getCreateAt());
        contentValues.put(AppConstant.COLUMN_ORDER_TOTAL_PRICE, order.getTotal_price());
        contentValues.put(AppConstant.COLUMN_ORDER_PERSON, order.getPerson());

        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_ORDER, null, contentValues);

        if (result == -1) {
            return -1;
        } else {
            ContentValues updateValues = new ContentValues();
            updateValues.put(AppConstant.COLUMN_RESTAURANT_STATUS, 1);
            String whereClause = AppConstant.COLUMN_RESTAURANT_ID + " = ?";
            String[] whereArgs = {String.valueOf(order.getIdTable())};
            this.sqLiteDatabase.update(AppConstant.TABLE_TABLE_RESTAURANT, updateValues, whereClause, whereArgs);
            return result;
        }
    }



    @Override
    public boolean createOrderDetail(OrderDetails order) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_ORDER_ID, order.getIdOrder());
        contentValues.put(AppConstant.COLUMN_ORDER_DETAIL_QUANTITY, order.getQuantity());
        contentValues.put(AppConstant.COLUMN_ORDER_DETAIL_DISCOUNT, order.getDiscount());
        contentValues.put(AppConstant.COLUMN_ORDER_DETAIL_PRICE, order.getPrice());
        contentValues.put(AppConstant.COLUMN_ORDER_DETAIL_NAME, order.getName());
        contentValues.put(AppConstant.COLUMN_ORDER_DETAIL_ID_MENU, order.getIdMenu());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_ORDER_DETAIL, null, contentValues);
        return result > 0;
    }

    @SuppressLint("Range")
    @Override
    public Order getOrderById(long id) {
        SQLiteDatabase sqLiteDatabase = mySqlHelper.getReadableDatabase();
        Order order = null;
        String sql = "SELECT * FROM " + AppConstant.TABLE_ORDER + " WHERE " + AppConstant.COLUMN_ORDER_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            int orderId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_ID));
            int userId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ID));
            int restaurantId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_ID));
            int orderStatus = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_STATUS));
            String orderCreatedAt = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_CREATE_AT));
            int orderTotalPrice = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_TOTAL_PRICE));
            int orderPerson = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_PERSON));
            order = new Order(orderId, userId, restaurantId, orderStatus, orderCreatedAt, orderTotalPrice, orderPerson);
        }
        cursor.close();
        sqLiteDatabase.close();
        return order;
    }

    public void updateOrderPerson(int orderId, int newPersonCount) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstant.COLUMN_ORDER_PERSON, newPersonCount);
        db.update(AppConstant.TABLE_ORDER, values, AppConstant.COLUMN_ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
        db.close();
    }

    @SuppressLint("Range")
    @Override
    public List<OrderDetails> getListOrderDetailsByIdOrder(long id) {
        SQLiteDatabase sqLiteDatabase = mySqlHelper.getReadableDatabase();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM " + AppConstant.TABLE_ORDER_DETAIL + " WHERE " + AppConstant.COLUMN_ORDER_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int orderDetailId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_ID));
                int orderId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_ID));
                int quantity = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_QUANTITY));
                int discount = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_DISCOUNT));
                int price = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_PRICE));
                String name = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_NAME));
                int idMenu = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_DETAIL_ID_MENU));
                OrderDetails orderDetails = new OrderDetails(orderDetailId, orderId, quantity, discount, price, name ,idMenu);
                orderDetailsList.add(orderDetails);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return orderDetailsList;
    }

    @SuppressLint("Range")
    @Override
    public Table getTableById(long id) {
        SQLiteDatabase sqLiteDatabase = mySqlHelper.getReadableDatabase();
        Table table = null;
        String query = "SELECT r.*, s." + AppConstant.COLUMN_STOREY_NAME
                + " FROM " + AppConstant.TABLE_TABLE_RESTAURANT + " r"
                + " JOIN " + AppConstant.TABLE_STOREY + " s"
                + " ON r." + AppConstant.COLUMN_STOREY_ID + " = s." + AppConstant.COLUMN_STOREY_ID + " WHERE " + AppConstant.COLUMN_RESTAURANT_ID + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            table = new Table(
                    cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_ID)),
                    cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_NAME)),
                    cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR)),
                    cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_STATUS)),
                    cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_ID)),
                    cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME))
            );
        }
        cursor.close();
        sqLiteDatabase.close();
        return table;
    }

    @Override
    public void removeOrderDetailByIdOrder(int orderId) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        db.delete(AppConstant.TABLE_ORDER_DETAIL, AppConstant.COLUMN_ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
        db.close();
    }

    @Override
    public void removeOrderById(int orderId) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        db.delete(AppConstant.TABLE_ORDER, AppConstant.COLUMN_ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
        db.close();
    }

    @Override
    public void updateStatusTable(int i) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(AppConstant.COLUMN_RESTAURANT_STATUS, 0);
        String whereClause = AppConstant.COLUMN_RESTAURANT_ID + " = ?";
        String[] whereArgs = {String.valueOf(i)};
        this.sqLiteDatabase.update(AppConstant.TABLE_TABLE_RESTAURANT, updateValues, whereClause, whereArgs);
    }
}

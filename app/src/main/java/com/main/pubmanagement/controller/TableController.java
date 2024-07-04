package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.TableDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableController implements TableDAO {

    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public TableController(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    @Override
    public long create(String name, int countChair , int idStorey) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_RESTAURANT_NAME, name);
        contentValues.put(AppConstant.COLUMN_RESTAURANT_STATUS, 0);
        contentValues.put(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR, countChair);
        contentValues.put(AppConstant.COLUMN_STOREY_ID, idStorey);
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_TABLE_RESTAURANT, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @Override
    public long update(int id, String name, int countChair) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_RESTAURANT_NAME, name);
        contentValues.put(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR, countChair);
        long result = sqLiteDatabase.update(AppConstant.TABLE_TABLE_RESTAURANT, contentValues, AppConstant.COLUMN_RESTAURANT_ID + " = ?", new String[]{String.valueOf(id)});
        return result == -1 ? -1 : result;
    }

    @Override
    public void delete(int id) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        sqLiteDatabase.delete(AppConstant.TABLE_TABLE_RESTAURANT, AppConstant.COLUMN_RESTAURANT_ID + " = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }


    @SuppressLint("Range")
    @Override
    public List<Order> getOrdersByRestaurantId(int restaurantId) {
        SQLiteDatabase sqLiteDatabase = mySqlHelper.getReadableDatabase();
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM " + AppConstant.TABLE_ORDER + " WHERE " + AppConstant.COLUMN_RESTAURANT_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(restaurantId)});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int orderId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_ID));
                int userId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ID));
                int orderStatus = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_STATUS));
                String orderCreatedAt = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_CREATE_AT));
                int orderTotalPrice = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_TOTAL_PRICE));
                int orderPerson = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_ORDER_PERSON));
                Order order = new Order(orderId, userId, restaurantId, orderStatus, orderCreatedAt, orderTotalPrice, orderPerson);
                orderList.add(order);
                cursor.moveToNext();
            }
        }

        cursor.close();
        sqLiteDatabase.close();

        return orderList;
    }

    @SuppressLint("Range")
    @Override
    public List<Table> getListTable() {
        List<Table> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String query = "SELECT r.*, s." + AppConstant.COLUMN_STOREY_NAME
                + " FROM " + AppConstant.TABLE_TABLE_RESTAURANT + " r"
                + " JOIN " + AppConstant.TABLE_STOREY + " s"
                + " ON r." + AppConstant.COLUMN_STOREY_ID + " = s." + AppConstant.COLUMN_STOREY_ID;
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);
        Table table;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                table = new Table(
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_STATUS)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME))
                );
                list.add(table);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    @SuppressLint("Range")
    @Override
    public List<Table> getListTableByIdStorey(int storeyId) {
        List<Table> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
//        String query = "SELECT * FROM " + AppConstant.TABLE_TABLE_RESTAURANT + " WHERE " + AppConstant.COLUMN_STOREY_ID + " = ?";
        String query = "SELECT r.*, s." + AppConstant.COLUMN_STOREY_NAME
                + " FROM " + AppConstant.TABLE_TABLE_RESTAURANT + " r"
                + " JOIN " + AppConstant.TABLE_STOREY + " s"
                + " ON r." + AppConstant.COLUMN_STOREY_ID + " = s." + AppConstant.COLUMN_STOREY_ID
                + " WHERE r." + AppConstant.COLUMN_STOREY_ID + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(storeyId)});
        Table table;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                table = new Table(
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_STATUS)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME))
                );
                list.add(table);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    @SuppressLint("Range")
    @Override
    public List<Table> getListTableByStatus(boolean status) {
        List<Table> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String query = "SELECT r.*, s." + AppConstant.COLUMN_STOREY_NAME
                + " FROM " + AppConstant.TABLE_TABLE_RESTAURANT + " r"
                + " JOIN " + AppConstant.TABLE_STOREY + " s"
                + " ON r." + AppConstant.COLUMN_STOREY_ID + " = s." + AppConstant.COLUMN_STOREY_ID
                + " WHERE r." + AppConstant.COLUMN_RESTAURANT_STATUS + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(status ? 1 : 0)});
        Table table;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                table = new Table(
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_STATUS)),
                        cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_ID)),
                        cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME))
                );
                list.add(table);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}

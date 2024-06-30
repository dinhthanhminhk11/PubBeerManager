package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.TableDAO;
import com.main.pubmanagement.db.MySqlHelper;
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
    public void create(String name) {

    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void create(int id) {

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

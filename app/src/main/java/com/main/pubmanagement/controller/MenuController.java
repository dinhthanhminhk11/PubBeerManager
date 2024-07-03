package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.MenuDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuController implements MenuDAO {
    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MenuController(Context context) {
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
    public List<Menu> getListMenu() {
        List<Menu> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_MENU;
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Menu menu = new Menu();
                menu.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_ID)));
                menu.setName(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_MENU_NAME)));
                menu.setPrice(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_PRICE)));
                menu.setUnit(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_UNIT)));
                menu.setIdMenuType(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_TYPE_ID)));
                menu.setDiscount(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_DISCOUNT)));
                menu.setCount(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_COUNT)));
                list.add(menu);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    @SuppressLint("Range")
    @Override
    public List<Menu> getListMenuByIdMenuType(int menuTypeId) {
        List<Menu> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String query = "SELECT * FROM " + AppConstant.TABLE_MENU + " WHERE " + AppConstant.COLUMN_MENU_TYPE_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(menuTypeId)});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Menu menu = new Menu();
                menu.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_ID)));
                menu.setName(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_MENU_NAME)));
                menu.setPrice(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_PRICE)));
                menu.setUnit(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_UNIT)));
                menu.setIdMenuType(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_TYPE_ID)));
                menu.setDiscount(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_DISCOUNT)));
                list.add(menu);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}

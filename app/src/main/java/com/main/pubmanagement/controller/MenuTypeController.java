package com.main.pubmanagement.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.MenuTypeDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.model.Storey;

import java.util.ArrayList;
import java.util.List;

public class MenuTypeController implements MenuTypeDAO {
    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MenuTypeController(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    @Override
    public long create(MenuType menuType) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_MENU_TYPE_NAME, menuType.getName());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_MENU_TYPE, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void create(int id) {

    }

    public long createMenu(Menu menu) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_MENU_NAME, menu.getName());
        contentValues.put(AppConstant.COLUMN_MENU_PRICE, menu.getPrice());
        contentValues.put(AppConstant.COLUMN_MENU_UNIT, menu.getUnit());
        contentValues.put(AppConstant.COLUMN_MENU_TYPE_ID, menu.getIdMenuType());
        contentValues.put(AppConstant.COLUMN_MENU_DISCOUNT, menu.getDiscount());
        if(menu.getContent() !=null){
            contentValues.put(AppConstant.COLUMN_MENU_CONTENT, menu.getContent());
        }
        contentValues.put(AppConstant.COLUMN_MENU_COUNT, menu.getCount());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_MENU, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @Override
    public List<Storey> getListMenuType() {
        List<Storey> list = new ArrayList<>();
        list.add(new Storey(0, "Tất cả"));
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_MENU_TYPE;
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Storey menu;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                menu = new Storey(cursor.getInt(0), cursor.getString(1));
                list.add(menu);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public List<Storey> getListMenuTypeNoAll() {
        List<Storey> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_MENU_TYPE;
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Storey menu;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                menu = new Storey(cursor.getInt(0), cursor.getString(1));
                list.add(menu);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}

package com.main.pubmanagement.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.MenuTypeDAO;
import com.main.pubmanagement.db.MySqlHelper;
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
    public void create(String name) {

    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void create(int id) {

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
}

package com.main.pubmanagement.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.StoreyDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Storey;

import java.util.ArrayList;
import java.util.List;

public class StoreyController implements StoreyDAO {

    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public StoreyController(Context context) {
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
    public List<Storey> getListStorey() {
        List<Storey> list = new ArrayList<>();
        list.add(new Storey(0, "Tất cả các tầng"));
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_STOREY;
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Storey storey;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                storey = new Storey(cursor.getInt(0), cursor.getString(1));
                list.add(storey);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}

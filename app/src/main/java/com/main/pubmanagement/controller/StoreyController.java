package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
    public long create(String name) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_STOREY_NAME, name);
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_STOREY, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @Override
    public long update(int id, String name) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_STOREY_NAME, name);
        long result = sqLiteDatabase.update(AppConstant.TABLE_STOREY, contentValues, AppConstant.COLUMN_STOREY_ID + " = ?", new String[]{String.valueOf(id)});
        return result == -1 ? -1 : result;
    }

    @Override
    public void delete(int id) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        sqLiteDatabase.delete(AppConstant.TABLE_STOREY, AppConstant.COLUMN_STOREY_ID + " = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public void deleteAllTable(int id) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        sqLiteDatabase.delete(AppConstant.TABLE_TABLE_RESTAURANT, AppConstant.COLUMN_RESTAURANT_ID + " = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
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

    @SuppressLint("Range")
    public List<Storey> getListStoreyNotAll() {
        List<Storey> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();

        String sql = "SELECT s." + AppConstant.COLUMN_STOREY_ID + ", s." + AppConstant.COLUMN_STOREY_NAME + ", " +
                "COUNT(r." + AppConstant.COLUMN_RESTAURANT_ID + ") AS restaurant_count " +
                "FROM " + AppConstant.TABLE_STOREY + " s " +
                "LEFT JOIN " + AppConstant.TABLE_TABLE_RESTAURANT + " r " +
                "ON s." + AppConstant.COLUMN_STOREY_ID + " = r." + AppConstant.COLUMN_STOREY_ID + " " +
                "GROUP BY s." + AppConstant.COLUMN_STOREY_ID + ", s." + AppConstant.COLUMN_STOREY_NAME;

        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int storeyId = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_ID));
                String storeyName = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME));
                int restaurantCount = cursor.getInt(cursor.getColumnIndex("restaurant_count"));
                Storey storey = new Storey(storeyId, storeyName, restaurantCount);
                list.add(storey);
                cursor.moveToNext();
            }
        }

        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}

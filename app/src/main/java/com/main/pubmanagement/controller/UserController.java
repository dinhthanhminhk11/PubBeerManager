package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.UserDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController implements UserDAO {

    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public UserController(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    @Override
    public User login(String username, String password) {
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + AppConstant.TABLE_USER
                + " WHERE " + AppConstant.COLUMN_USER_USERNAME + " = '" + username + "' and " + AppConstant.COLUMN_USER_PASSWORD + " = '" + password + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(6)),
                    Integer.parseInt(cursor.getString(7))
            );
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return user;
    }

    @Override
    public long register(User user) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_USER_NAME, user.getName());
        contentValues.put(AppConstant.COLUMN_USER_PHONE, user.getPhone());
        contentValues.put(AppConstant.COLUMN_USER_CCCD, user.getCardId());
        contentValues.put(AppConstant.COLUMN_USER_USERNAME, user.getUsername());
        contentValues.put(AppConstant.COLUMN_USER_PASSWORD, user.getPassword());
        contentValues.put(AppConstant.COLUMN_USER_ROLE, user.getRole());
        contentValues.put(AppConstant.COLUMN_USER_SHIFT, user.getShift());
        contentValues.put(AppConstant.COLUMN_USER_SALARY, user.getSalary());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_USER, null, contentValues);
        return result == -1 ? -1 : result;
    }

    public long editMember(User user) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_USER_NAME, user.getName());
        contentValues.put(AppConstant.COLUMN_USER_PHONE, user.getPhone());
        contentValues.put(AppConstant.COLUMN_USER_CCCD, user.getCardId());
        contentValues.put(AppConstant.COLUMN_USER_USERNAME, user.getUsername());
        contentValues.put(AppConstant.COLUMN_USER_PASSWORD, user.getPassword());
        contentValues.put(AppConstant.COLUMN_USER_ROLE, user.getRole());
        contentValues.put(AppConstant.COLUMN_USER_SHIFT, user.getShift());
        contentValues.put(AppConstant.COLUMN_USER_SALARY, user.getSalary());
        long result = sqLiteDatabase.update(AppConstant.TABLE_USER, contentValues, AppConstant.COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
        return result == -1 ? -1 : result;
    }

    public long updateProfile(User user) {
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_USER_NAME, user.getName());
        contentValues.put(AppConstant.COLUMN_USER_PHONE, user.getPhone());
        contentValues.put(AppConstant.COLUMN_USER_CCCD, user.getCardId());
        contentValues.put(AppConstant.COLUMN_USER_USERNAME, user.getUsername());
        contentValues.put(AppConstant.COLUMN_USER_PASSWORD, user.getPassword());
        long result = sqLiteDatabase.update(AppConstant.TABLE_USER, contentValues, AppConstant.COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
        return result == -1 ? -1 : result;
    }

    public void deleteUserById(int id) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        db.delete(AppConstant.TABLE_USER, AppConstant.COLUMN_USER_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<User> getUsersWithRoleOne() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();

        String query = "SELECT * FROM " + AppConstant.TABLE_USER + " WHERE " + AppConstant.COLUMN_USER_ROLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{"1"});

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_NAME)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_PHONE)));
                user.setCardId(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_CCCD)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_PASSWORD)));
                user.setRole(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ROLE)));
                user.setShift(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_SHIFT)));
                user.setSalary(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_SALARY)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

    @SuppressLint("Range")
    public User getUserById(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();

        String query = "SELECT " + AppConstant.COLUMN_USER_ID + ", "
                + AppConstant.COLUMN_USER_NAME + ", "
                + AppConstant.COLUMN_USER_PHONE + ", "
                + AppConstant.COLUMN_USER_CCCD + ", "
                + AppConstant.COLUMN_USER_USERNAME + ", "
                + AppConstant.COLUMN_USER_PASSWORD + ", "
                + AppConstant.COLUMN_USER_ROLE + ", "
                + AppConstant.COLUMN_USER_SHIFT + ", "
                + AppConstant.COLUMN_USER_SALARY
                + " FROM " + AppConstant.TABLE_USER
                + " WHERE " + AppConstant.COLUMN_USER_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_NAME)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_PHONE)));
            user.setCardId(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_CCCD)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_USER_PASSWORD)));
            user.setRole(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_ROLE)));
            user.setShift(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_SHIFT)));
            user.setSalary(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_USER_SALARY)));
        }

        cursor.close();
        db.close();

        return user;
    }

}

package com.main.pubmanagement.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.UserDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.User;

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
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5))
            );
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return user;
    }

    @Override
    public void register(User user) {

    }
}

package com.main.pubmanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.main.pubmanagement.constant.AppConstant;

public class MySqlHelper extends SQLiteOpenHelper {
    public MySqlHelper(Context context) {
        super(context, AppConstant.DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE " + AppConstant.TABLE_USER
                + "("
                + AppConstant.COLUMN_USER_ID + " INTEGER PRIMARY KEY , "
                + AppConstant.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + AppConstant.COLUMN_USER_USERNAME + " TEXT NOT NULL, "
                + AppConstant.COLUMN_USER_PASSWORD + " TEXT NOT NULL, "
                + AppConstant.COLUMN_USER_ROLE + " INTEGER NOT NULL, "
                + AppConstant.COLUMN_USER_SHIFT + " INTEGER NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(sql1);


        sql1 = "INSERT INTO " + AppConstant.TABLE_USER + " VALUES ( null ,'Đinh Thanh Minh','dinhthanhminh' ,'123456' , 0 , 0)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_USER + " VALUES ( null ,'Đinh Thanh Minh Nhan Vien','staffdinhminh' ,'123456' , 1 , 1)";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}

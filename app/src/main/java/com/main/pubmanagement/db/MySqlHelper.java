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
        String sql1 = "CREATE TABLE " + AppConstant.TABLE_USER + "(" + AppConstant.COLUMN_USER_ID + " INTEGER PRIMARY KEY , " + AppConstant.COLUMN_USER_NAME + " TEXT NOT NULL, " + AppConstant.COLUMN_USER_USERNAME + " TEXT NOT NULL, " + AppConstant.COLUMN_USER_PASSWORD + " TEXT NOT NULL, " + AppConstant.COLUMN_USER_ROLE + " INTEGER NOT NULL, " + AppConstant.COLUMN_USER_SHIFT + " INTEGER NOT NULL" + ")";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_USER + " VALUES ( null ,'Đinh Thanh Minh','dinhthanhminh' ,'123456' , 0 , 0)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_USER + " VALUES ( null ,'Đinh Thanh Minh Nhan Vien','staffdinhminh' ,'123456' , 1 , 1)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "CREATE TABLE " + AppConstant.TABLE_STOREY + "(" + AppConstant.COLUMN_STOREY_ID + " INTEGER PRIMARY KEY , " + AppConstant.COLUMN_STOREY_NAME + " TEXT NOT NULL " + ")";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_STOREY + " VALUES ( null ,'Tầng 1')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_STOREY + " VALUES ( null ,'Tầng 2')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_STOREY + " VALUES ( null ,'Tầng 3')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_STOREY + " VALUES ( null ,'Tầng 4')";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "CREATE TABLE " + AppConstant.TABLE_TABLE_RESTAURANT + "(" + AppConstant.COLUMN_RESTAURANT_ID + " INTEGER PRIMARY KEY , "
                + AppConstant.COLUMN_RESTAURANT_NAME + " TEXT NOT NULL, "
                + AppConstant.COLUMN_RESTAURANT_COUNT_CHAIR + " INTEGER NOT NULL, "
                + AppConstant.COLUMN_RESTAURANT_STATUS + " INTEGER NOT NULL, "
                + AppConstant.COLUMN_STOREY_ID + " INTEGER REFERENCES "
                + AppConstant.TABLE_STOREY + "( " + AppConstant.COLUMN_STOREY_ID + ")"
                + ")";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'01' , 6, 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'02' , 3, 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'03' , 2, 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'04' , 4, 0 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'05' , 4, 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'06' , 4, 0 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'07' , 4, 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'08' , 4, 0 , 4)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'09' , 4, 0 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'10' , 4, 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'11' , 4, 0 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_TABLE_RESTAURANT + " VALUES ( null ,'12' , 4, 0 , 3)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "CREATE TABLE " + AppConstant.TABLE_MENU_TYPE + "(" + AppConstant.COLUMN_MENU_TYPE_ID + " INTEGER PRIMARY KEY , " + AppConstant.COLUMN_MENU_TYPE_NAME + " TEXT NOT NULL " + ")";
        sqLiteDatabase.execSQL(sql1);


        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Craft Beer')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Đồ Khai Vị')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Rau - Salad')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Món Chiên - Xào')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Món Nướng - Hầm')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Các Món Cừu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Các Món Lẩu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Cơm - Mỳ')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU_TYPE + " VALUES ( null ,'Đồ Uống Khác')";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "CREATE TABLE " + AppConstant.TABLE_MENU + "("
                + AppConstant.COLUMN_MENU_ID + " INTEGER PRIMARY KEY ,"
                + AppConstant.COLUMN_MENU_NAME + " TEXT NOT NULL ,"
                + AppConstant.COLUMN_MENU_PRICE + " INTEGER NOT NULL ,"
                + AppConstant.COLUMN_MENU_UNIT + " INTEGER NOT NULL , "
                + AppConstant.COLUMN_MENU_TYPE_ID + " INTEGER REFERENCES " + AppConstant.TABLE_MENU_TYPE + "( " + AppConstant.COLUMN_MENU_TYPE_ID + "),"
                + AppConstant.COLUMN_MENU_DISCOUNT + " INTEGER"
                + ")";
        sqLiteDatabase.execSQL(sql1);


        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BIA vàng truyền thống - PILSNER' , 280000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BIA DOUBLE IPA - DOUBLE IPE' , 375000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BIA PALE ALE - IPA' , 320000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BIA TÁO - CIDER APPLE' , 375000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BIA WHEAT - BEER WHEAT' , 320000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'HOÀNG HÔN NGỌT NGÀO SKY' , 450000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'KHÓI THUỐC ĐỢI CHỜ' , 450000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'BAY QUA VÙNG BIỂN ĐEN - BLACK DIAMOND' , 450000 , 0 , 1)";
        sqLiteDatabase.execSQL(sql1);


// ĐỒ KHAI VỊ
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ô Liu Xanh' , 55000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ô Liu Đen' , 55000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cà Chua Bi' , 35000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Salad PILOT' , 75000 , 1 , 2  )";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đậu Tẩm Hành' , 75000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Trâu Xé PILOT' , 145000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Khoai Tây Chiên' , 55000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Khoai Lang Chiên' , 45000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Phomai Dây' , 95000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đồ Nguội Tổng Hợp' , 225000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Xúc Xích Tươi Nướng' , 55000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sụn Gà Rang Muối' , 110000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sụn Gà Chiên Mắm' , 110000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Bò Muối Chua' , 150000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lưỡi Ngỗng Xông Khói' , 125000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sườn Heo Cháy Tỏi' , 2255000 , 1 , 2)";
        sqLiteDatabase.execSQL(sql1);


        // salad

        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Rau Muống Xào Tỏi' , 45000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Xanh Xào Nấm' , 50000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mướp Đắng Ruốc' , 55000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Xoắn Xào Tỏi' , 55000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Xoán Xào Top Mỡ' , 70000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Xoăn Xào Trứng' , 70000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Cầu Vòng Xào Tỏi' , 45000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cải Cồng Vòng Luộc Chấm Trứng' , 55000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ngọn Đậu Hà Lan Xào Toi' , 60000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ngọn Đậu Hà Lan Xào Top Mỡ' , 75000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ngọn Đậu Hà Lan Xào Trứng' , 75000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Luộc Chấm Trứng' , 55000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Xòa Tỏi' , 45000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Xào Trứng' , 75000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Luộc Chấm Trứng' , 55000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Xào Tỏi' , 45000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Cải Baby Xào Tóp Mỡ' , 60000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Rau Bò Khai Xào Tỏi' , 60000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Rau Bò Khai Xào Trứng' , 65000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Rau Bò Khai Xào Tóp Mỡ' , 70000 , 1 , 3)";
        sqLiteDatabase.execSQL(sql1);


        // chiên sào
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đậu Nhật Chiên Trứng Muối' , 55000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đậu Nhật Chiên Xù' , 50000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đậu Nhật Xốt Tứ Xuyên' , 60000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Trâu Xào Rau Muống' , 145000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Trâu Xào Măng Trúc' , 165000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Bò Xào Rau Rừng' , 165000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bắp Bò Xào Rau Bò Khai' , 179000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mực Một Nắng Chiên Bơ Tỏi' , 295000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mực Một Nắng Nướng Muối Ớt' , 295000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mực Một Nắng Nướng Mọi' , 295000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Gà Rang Muối Tiêu' , 210000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mực Khô Chiên Bơ Tỏi' , 195000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mực Khô Nướng' , 185000 , 1 , 4)";
        sqLiteDatabase.execSQL(sql1);

        // Món Nướng Hầm

        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chân Giò Hầm Kiểu Đức' , 325000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chân Giò Chiên Giòn Bì' , 325000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chân Giò Bát Bảo' , 385000 , 3 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cá Nhồng Nướng Muối Ớt' , 60000 , 2 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bò Mỹ Bản Gang' , 250000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Bò Mỹ Bỏ Lò Phô Mai' , 245000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Trâu Nướng Lá Lốt' , 175000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Heo Nướng Kiểu Nga' , 150000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Heo Nướng Chả Hành' , 125000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Ba Chỉ Nướng Giềng Mẻ' , 145000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sườn Heo Nướng Tảng' , 250000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sụn Non Nướng Thảo Mộc' , 165000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Gà Chiên Mắm' , 210000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chim Câu Nướng Ngũ Vị' , 195000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chim Câu Xúc Phồng Tôm' , 185000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Gà Nguyên Con Nướng Lu' , 395000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Gà Tiềm Bát Bảo' , 410000 , 1 , 5)";
        sqLiteDatabase.execSQL(sql1);


        // món cừu
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Xông Hơi' , 210000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Hấp Sả Gừng' , 199000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Hấp Giả Cầy' , 199000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Nướng Tảng' , 220000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Sườn Cừu Nướng' , 550000 , 4 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Nướng Kiểu Nga' , 225000 , 1 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Cháy Tỏi' , 225000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Xào Xả Ớt' , 199000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cừu Nướng Bản Gang' , 210000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Xương Cừu Nấu Cháu Đậu Xanh' , 60000 , 6 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Cừu Tứ Xuyên' , 580000 , 5 , 6)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Đùi Cừu Đút Lò(Nguyên Chiếc)' , 550000 , 4 , 6)";
        sqLiteDatabase.execSQL(sql1);


//Các Món Lẩu
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Gà Nấm Nếp' , 480000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Hải Sản Kiểu Thái' , 580000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Riêu Cua Bắp Bò, Sườn Sụn' , 480000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Cua Đồng' , 450000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Lẩu Cá Quả Rau Cải' , 380000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cá Chép Om Dưa' , 385000 , 3 , 7)";
        sqLiteDatabase.execSQL(sql1);


        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cơm Rang Thập Cẩm' , 75000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cơm Rang Muối Tỏi' , 35000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Cơm Rang Trứng' , 40000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mỳ Xào Hải Sản' , 95000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mỳ Xào Bò' , 95000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Mỳ Ý Sốt Bồ Hầm' , 95000 , 1 , 8)";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Nước Ép Cam/Dừa' , 50000 , 7 , 9)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Chanh Tươi' , 35000 , 7 , 9)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Nước Ngọt' , 20000 , 9 , 9)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + AppConstant.TABLE_MENU + "(" + AppConstant.COLUMN_MENU_ID + "," + AppConstant.COLUMN_MENU_NAME + "," + AppConstant.COLUMN_MENU_PRICE + "," + AppConstant.COLUMN_MENU_UNIT + "," + AppConstant.COLUMN_MENU_TYPE_ID + ")" + " VALUES ( null ,'Nước Suối' , 15000 , 8 , 9)";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_USER);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_STOREY);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_TABLE_RESTAURANT);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_MENU_TYPE);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_MENU);
        onCreate(sqLiteDatabase);
    }
}

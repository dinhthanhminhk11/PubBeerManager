package com.main.pubmanagement.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.BillDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.BillInfo;

import java.util.ArrayList;
import java.util.List;

public class BillController implements BillDAO {
    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public BillController(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    @Override
    public long createBill(Bill bill) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_BILL_TYPE, bill.getType());
        contentValues.put(AppConstant.COLUMN_USER_ID, bill.getIdUser());
        contentValues.put(AppConstant.COLUMN_RESTAURANT_ID, bill.getIdTable());
        contentValues.put(AppConstant.COLUMN_BILL_PRICE, bill.getPrice());
        contentValues.put(AppConstant.COLUMN_BILL_PRICE_DISCOUNT, bill.getPriceDiscount());
        contentValues.put(AppConstant.COLUMN_BILL_TIME, bill.getTime());
        contentValues.put(AppConstant.COLUMN_BILL_COUNT_PERSON, bill.getCountPerson());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_BILL, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @Override
    public long createBillInfo(BillInfo bill) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.COLUMN_BILL_ID, bill.getIdBill());
        contentValues.put(AppConstant.COLUMN_MENU_ID, bill.getIdMenu());
        contentValues.put(AppConstant.COLUMN_BILL_INFO_QUANTITY, bill.getQuantity());
        contentValues.put(AppConstant.COLUMN_BILL_INFO_DISCOUNT, bill.getDiscount());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_BILL_INFO, null, contentValues);
        return result == -1 ? -1 : result;
    }

    @SuppressLint("Range")
    @Override
    public List<Bill> getListBillById(int idUser) {
        List<Bill> billList = new ArrayList<>();
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();

        String query = "SELECT b." + AppConstant.COLUMN_BILL_ID + ", b." + AppConstant.COLUMN_BILL_TYPE + ", " +
                "b." + AppConstant.COLUMN_BILL_PRICE + ", b." + AppConstant.COLUMN_BILL_PRICE_DISCOUNT + ", " +
                "b." + AppConstant.COLUMN_BILL_TIME + ", b." + AppConstant.COLUMN_BILL_COUNT_PERSON + ", " +
                "r." + AppConstant.COLUMN_RESTAURANT_NAME + ", s." + AppConstant.COLUMN_STOREY_NAME +
                " FROM " + AppConstant.TABLE_BILL + " b" +
                " JOIN " + AppConstant.TABLE_TABLE_RESTAURANT + " r ON b." + AppConstant.COLUMN_RESTAURANT_ID + " = r." + AppConstant.COLUMN_RESTAURANT_ID +
                " JOIN " + AppConstant.TABLE_STOREY + " s ON r." + AppConstant.COLUMN_STOREY_ID + " = s." + AppConstant.COLUMN_STOREY_ID +
                " WHERE b." + AppConstant.COLUMN_USER_ID + " = ?";
        Log.e("Minh", "qurey " + query);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idUser)});

        if (cursor.moveToFirst()) {
            do {
                Bill bill = new Bill();
                bill.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_ID)));
                bill.setType(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_TYPE)));
                bill.setPrice(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_PRICE)));
                bill.setPriceDiscount(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_PRICE_DISCOUNT)));
                bill.setTime(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_BILL_TIME)));
                bill.setCountPerson(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_COUNT_PERSON)));
                bill.setNameTable(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_RESTAURANT_NAME)));
                bill.setStoreyName(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_STOREY_NAME)));
                billList.add(bill);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return billList;
    }

    @SuppressLint("Range")
    public List<BillInfo> getBillInfoByBillId(int billId) {
        List<BillInfo> billInfoList = new ArrayList<>();
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();

        String query = "SELECT bi." + AppConstant.COLUMN_BILL_INFO_ID + ", bi." + AppConstant.COLUMN_BILL_ID + ", " +
                "bi." + AppConstant.COLUMN_MENU_ID + ", bi." + AppConstant.COLUMN_BILL_INFO_QUANTITY + ", " +
                "bi." + AppConstant.COLUMN_BILL_INFO_DISCOUNT + ", m." + AppConstant.COLUMN_MENU_NAME + ", " +
                "m." + AppConstant.COLUMN_MENU_PRICE +
                " FROM " + AppConstant.TABLE_BILL_INFO + " bi" +
                " JOIN " + AppConstant.TABLE_MENU + " m ON bi." + AppConstant.COLUMN_MENU_ID + " = m." + AppConstant.COLUMN_MENU_ID +
                " WHERE bi." + AppConstant.COLUMN_BILL_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(billId)});

        if (cursor.moveToFirst()) {
            do {
                BillInfo billInfo = new BillInfo();
                billInfo.setId(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_INFO_ID)));
                billInfo.setIdBill(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_ID)));
                billInfo.setIdMenu(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_ID)));
                billInfo.setQuantity(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_INFO_QUANTITY)));
                billInfo.setDiscount(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_BILL_INFO_DISCOUNT)));
                billInfo.setNameMenu(cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_MENU_NAME)));
                billInfo.setPrice(cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_MENU_PRICE)));

                billInfoList.add(billInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return billInfoList;
    }

    @SuppressLint("Range")
    public int getTotalRevenueByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT SUM(" + AppConstant.COLUMN_BILL_PRICE + ") AS totalRevenue FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(cursor.getColumnIndex("totalRevenue"));
        }

        cursor.close();
        db.close();
        return totalRevenue;
    }

    @SuppressLint("Range")
    public int getTotalDiscountRevenueByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT SUM(" + AppConstant.COLUMN_BILL_PRICE_DISCOUNT + ") AS totalDiscountRevenue FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        int totalDiscountRevenue = 0;
        if (cursor.moveToFirst()) {
            totalDiscountRevenue = cursor.getInt(cursor.getColumnIndex("totalDiscountRevenue"));
        }

        cursor.close();
        db.close();
        return totalDiscountRevenue;
    }

    @SuppressLint("Range")
    public int getTotalCustomersServedByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT SUM(" + AppConstant.COLUMN_BILL_COUNT_PERSON + ") AS totalCustomersServed FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        int totalCustomersServed = 0;
        if (cursor.moveToFirst()) {
            totalCustomersServed = cursor.getInt(cursor.getColumnIndex("totalCustomersServed"));
        }

        cursor.close();
        db.close();
        return totalCustomersServed;
    }

    @SuppressLint("Range")
    public int getTotalCustomersBeingServedByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT SUM(" + AppConstant.COLUMN_ORDER_PERSON + ") AS totalCustomersBeingServed FROM " + AppConstant.TABLE_ORDER +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ? AND " + AppConstant.COLUMN_ORDER_STATUS + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), "0"}); // Giả sử trạng thái 1 là đang phục vụ

        int totalCustomersBeingServed = 0;
        if (cursor.moveToFirst()) {
            totalCustomersBeingServed = cursor.getInt(cursor.getColumnIndex("totalCustomersBeingServed"));
        }

        cursor.close();
        db.close();
        return totalCustomersBeingServed;
    }

    @SuppressLint("Range")
    public int getTotalBillsByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) AS totalBills FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        int totalBills = 0;
        if (cursor.moveToFirst()) {
            totalBills = cursor.getInt(cursor.getColumnIndex("totalBills"));
        }

        cursor.close();
        db.close();
        return totalBills;
    }

    @SuppressLint("Range")
    public int getTotalDiscountBillsByUserId(int userId) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) AS totalDiscountBills FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ? AND " + AppConstant.COLUMN_BILL_PRICE_DISCOUNT + " > 0";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        int totalDiscountBills = 0;
        if (cursor.moveToFirst()) {
            totalDiscountBills = cursor.getInt(cursor.getColumnIndex("totalDiscountBills"));
        }

        cursor.close();
        db.close();
        return totalDiscountBills;
    }

    // Hàm lấy tổng số hóa đơn theo loại và user_id
    @SuppressLint("Range")
    public int getTotalBillsByTypeAndUserId(int userId, int type) {
        SQLiteDatabase db = mySqlHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) AS totalBillsByType FROM " + AppConstant.TABLE_BILL +
                " WHERE " + AppConstant.COLUMN_USER_ID + " = ? AND " + AppConstant.COLUMN_BILL_TYPE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(type)});

        int totalBillsByType = 0;
        if (cursor.moveToFirst()) {
            totalBillsByType = cursor.getInt(cursor.getColumnIndex("totalBillsByType"));
        }

        cursor.close();
        db.close();
        return totalBillsByType;
    }

    public int getTotalCashBillsByUserId(int userId) {
        return getTotalBillsByTypeAndUserId(userId, 0);
    }

    public int getTotalCardBillsByUserId(int userId) {
        return getTotalBillsByTypeAndUserId(userId, 2);
    }

    public int getTotalCombinedBillsByUserId(int userId) {
        return getTotalBillsByTypeAndUserId(userId, 3);
    }

}

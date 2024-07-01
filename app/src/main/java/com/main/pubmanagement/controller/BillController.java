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
                "r." + AppConstant.COLUMN_RESTAURANT_NAME +
                " FROM " + AppConstant.TABLE_BILL + " b" +
                " JOIN " + AppConstant.TABLE_TABLE_RESTAURANT + " r ON b." + AppConstant.COLUMN_RESTAURANT_ID + " = r." + AppConstant.COLUMN_RESTAURANT_ID +
                " WHERE b." + AppConstant.COLUMN_USER_ID + " = ?";
        Log.e("Minh" , "qurey " + query);
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
                billList.add(bill);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return billList;
    }
}

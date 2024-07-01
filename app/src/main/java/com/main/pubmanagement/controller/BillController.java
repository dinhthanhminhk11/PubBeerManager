package com.main.pubmanagement.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.dao.BillDAO;
import com.main.pubmanagement.db.MySqlHelper;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.BillInfo;

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
}

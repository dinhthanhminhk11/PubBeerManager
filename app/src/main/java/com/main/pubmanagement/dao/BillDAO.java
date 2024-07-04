package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.BillInfo;

import java.util.List;

public interface BillDAO {
    long createBill(Bill bill);

    long createBillInfo(BillInfo bill);

    List<Bill> getListBillById(int idUser, long startDate, long endDate);
}

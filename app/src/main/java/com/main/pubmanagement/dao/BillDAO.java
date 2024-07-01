package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.BillInfo;

public interface BillDAO {
    long createBill(Bill bill);
    long createBillInfo(BillInfo bill);
}

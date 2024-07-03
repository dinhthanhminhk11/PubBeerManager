package com.main.pubmanagement.ui.fragment.manager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private BillController billController;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");

    @Override
    protected FragmentHomeBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater , container , false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        billController = new BillController(getActivity());

        binding.priceBill.setText(decimalFormat.format(billController.getTotalRevenueByUserId(0)) + " ₫");
        binding.bill.setText(billController.getTotalBillsByUserId(0) + " hóa đơn");
        binding.personActive.setText(billController.getTotalCustomersBeingServedByUserId(0) + " khách");
        binding.table.setText(billController.getActiveTables() + "/" + billController.getTotalTables() +" bàn đang hoạt động");
        binding.priceBillActive.setText(decimalFormat.format(billController.getTotalAmountBeingServedByUserId(0)) + " ₫");
        binding.billActive.setText(billController.getTotalOrdersByUserId(0) + " hóa đơn đang phục vụ");


        binding.countDiscountBillprice.setText(decimalFormat.format(billController.getTotalDiscountRevenueByUserId(0)) + " ₫");
        binding.countBillDiscount.setText(billController.getTotalDiscountBillsByUserId(0) + "");
        binding.countTypeBill.setText(billController.getTotalBillsByTypeAndUserId(0, 0) + "");
        binding.countTypeBill2.setText(billController.getTotalCashBillsByUserId(0) + "");
        binding.countTypeBill3.setText(billController.getTotalCardBillsByUserId(0) + "");
        binding.countTypeBill4.setText(billController.getTotalCombinedBillsByUserId(0) + "");
    }
}
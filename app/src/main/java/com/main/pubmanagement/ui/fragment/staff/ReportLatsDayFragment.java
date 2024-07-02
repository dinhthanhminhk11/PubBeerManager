package com.main.pubmanagement.ui.fragment.staff;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.FragmentReportLatsDayBinding;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;

import java.text.DecimalFormat;


public class ReportLatsDayFragment extends BaseFragment<FragmentReportLatsDayBinding> {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private BillController billController;

    @Override
    protected FragmentReportLatsDayBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentReportLatsDayBinding.inflate(inflater, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        billController = new BillController(getActivity());

        int idUser = MySharedPreferences.getInstance(getActivity()).getInt(AppConstant.COLUMN_USER_ID, 0);

        binding.typePayment.setText(decimalFormat.format(billController.getTotalRevenueByUserId(idUser)) + " ₫");
        binding.countDiscountBillprice.setText(decimalFormat.format(billController.getTotalDiscountRevenueByUserId(idUser)) + " ₫");
        binding.time.setText(billController.getTotalCustomersServedByUserId(idUser) + "");
        binding.countPerson.setText(billController.getTotalCustomersBeingServedByUserId(idUser) + "");
        binding.countBill.setText(billController.getTotalBillsByUserId(idUser) + "");
        binding.countBillDiscount.setText(billController.getTotalDiscountBillsByUserId(idUser) + "");
        binding.countTypeBill.setText(billController.getTotalBillsByTypeAndUserId(idUser, 0) + "");
        binding.countTypeBill2.setText(billController.getTotalCashBillsByUserId(idUser) + "");
        binding.countTypeBill3.setText(billController.getTotalCardBillsByUserId(idUser) + "");
        binding.countTypeBill4.setText(billController.getTotalCombinedBillsByUserId(idUser) + "");


    }
}
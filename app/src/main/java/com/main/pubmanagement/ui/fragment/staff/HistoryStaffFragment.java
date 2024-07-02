package com.main.pubmanagement.ui.fragment.staff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.FragmentHistoryStaffBinding;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.adapter.HistoryBillAdapter;

import java.util.Collections;
import java.util.List;


public class HistoryStaffFragment extends BaseFragment<FragmentHistoryStaffBinding> {
    private HistoryBillAdapter historyBillAdapter;
    private BillController billController;

    @Override
    protected FragmentHistoryStaffBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHistoryStaffBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyBillAdapter = new HistoryBillAdapter();
        billController = new BillController(getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(historyBillAdapter);
        List<Bill> billList = billController.getListBillById(MySharedPreferences.getInstance(getActivity()).getInt(AppConstant.COLUMN_USER_ID, 0));
        Collections.reverse(billList);
        historyBillAdapter.setData(billList);
    }
}
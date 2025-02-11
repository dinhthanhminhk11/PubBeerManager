package com.main.pubmanagement.ui.fragment.staff;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.FragmentHistoryStaffBinding;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.adapter.HistoryBillAdapter;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class HistoryStaffFragment extends BaseFragment<FragmentHistoryStaffBinding> {
    private HistoryBillAdapter historyBillAdapter;
    private BillController billController;
    private String checkStartDate;
    private String checkEndDate;
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




        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        builder.setCalendarConstraints(constraintBuilder.build());
        builder.setTheme(R.style.ThemeOverlay_App_DatePicker);
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder
                .setTitleText("Chọn ngày")
                .setPositiveButtonText("Lưu")
                .setNegativeButtonText("Thoát")
                .setSelection(new Pair<>(MaterialDatePicker.todayInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds()))
                .build();

        binding.choseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!materialDatePicker.isVisible()) {
                    materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");

                    materialDatePicker.getLifecycle().addObserver(new DefaultLifecycleObserver() {
                        @Override
                        public void onDestroy(@NonNull LifecycleOwner owner) {
                            materialDatePicker.getLifecycle().removeObserver(this);
                        }
                    });
                }

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        Long startDate = selection.first;
                        Long endDate = selection.second;

                        checkStartDate = DateFormat.format("dd/MM/yyyy", new Date(startDate)).toString();
                        checkEndDate = DateFormat.format("dd/MM/yyyy", new Date(endDate)).toString();
                        binding.name.setText(checkStartDate + " - " + checkEndDate);

                        if (checkStartDate.equals(checkEndDate)) {
                            binding.name.setText("Hôm nay");
                            endDate = startDate + Duration.ofDays(1).toMillis() - 1;
                        }

                        List<Bill> filteredBills = billController.getListBill(startDate, endDate);
                        updateBillList(filteredBills);
                    }
                });
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        long todayStart = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli();
        long todayEnd = todayStart + Duration.ofDays(1).toMillis() - 1;
        List<Bill> todayBills =  billController.getListBillById(MySharedPreferences.getInstance(getActivity()).getInt(AppConstant.COLUMN_USER_ID, 0) ,todayStart, todayEnd);
        updateBillList(todayBills);


    }

    private void updateBillList(List<Bill> billList) {
        Collections.reverse(billList);
        historyBillAdapter.setData(billList);
    }
}
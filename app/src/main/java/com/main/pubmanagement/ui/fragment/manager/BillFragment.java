package com.main.pubmanagement.ui.fragment.manager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.FragmentBillBinding;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.Payment;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.adapter.HistoryBillAdapter;
import com.main.pubmanagement.ui.view.bottomsheet.BottomSheetFilterTypeBill;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class BillFragment extends BaseFragment<FragmentBillBinding> {
    private HistoryBillAdapter historyBillAdapter;
    private BillController billController;
    private String checkStartDate;
    private String checkEndDate;
    private UserController userController;
    private List<User> userList;
    private Long startDateFinal;
    private Long endDateFinal;

    @Override
    protected FragmentBillBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBillBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyBillAdapter = new HistoryBillAdapter();
        billController = new BillController(getActivity());
        userController = new UserController(getActivity());
        userList = new ArrayList<>();
        userList.addAll(userController.getUsersWithRoleOne());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(historyBillAdapter);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
// constraintBuilder.setValidator(DateValidatorPointForward.now());

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
            public void onClick(View view) {
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

                        startDateFinal = startDate;
                        endDateFinal = endDate;

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

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFilterTypeBill bottomSheetFilter = new BottomSheetFilterTypeBill(userList, getActivity(), new BottomSheetFilterTypeBill.EventClick() {
                    @Override
                    public void onCLickFilter(List<User> selectedUsers, List<Payment> selectedPayments) {
                        List<Bill> filteredBills = billController.getListBill(startDateFinal, endDateFinal, selectedUsers, selectedPayments);
                        updateBillList(filteredBills);
                    }
                });
                bottomSheetFilter.show();
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        long todayStart = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli();
        long todayEnd = todayStart + Duration.ofDays(1).toMillis() - 1;

        startDateFinal = todayStart;
        endDateFinal = todayEnd;

        List<Bill> todayBills = billController.getListBill(todayStart, todayEnd);
        updateBillList(todayBills);
    }

    private void updateBillList(List<Bill> billList) {
        Collections.reverse(billList);
        historyBillAdapter.setData(billList);
    }
}
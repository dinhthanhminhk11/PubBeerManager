package com.main.pubmanagement.ui.view.bottomsheet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.main.pubmanagement.R;
import com.main.pubmanagement.model.Payment;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.adapter.AdapterListTypeFilter;
import com.main.pubmanagement.ui.adapter.AdapterListUserFilter;
import com.main.pubmanagement.ui.adapter.PaymentAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFilterTypeBill extends BottomSheetDialog {
    private EventClick eventClick;
    private ImageView imgCancel;
    private TextView tvReset;
    private RecyclerView listPerson;
    private RecyclerView listType;
    private AppCompatButton btnFilter;
    private List<Payment> listPayment;
    private List<User> listUser;
    private int payment = 0;


    private List<Payment> listPaymentCallback;
    private List<User> listUserCallback;


    public BottomSheetFilterTypeBill(List<User> listUser, @NonNull Context context, EventClick eventClick) {
        super(context);
        this.eventClick = eventClick;
        this.listUser = listUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.fragment_filter);
        getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        initView();
    }

    private void initView() {
        imgCancel = findViewById(R.id.imgCancel);
        tvReset = findViewById(R.id.tvReset);
        listPerson = findViewById(R.id.listPerson);
        listType = findViewById(R.id.listType);
        btnFilter = findViewById(R.id.btnFilter);
        listPayment = new ArrayList<>();
        listUserCallback = new ArrayList<>();
        listPaymentCallback = new ArrayList<>();
        listPayment.add(new Payment(1, "Thanh toán tiền mặt"));
        listPayment.add(new Payment(2, "Chuyển khoản"));
        listPayment.add(new Payment(3, "Thẻ"));
        listPayment.add(new Payment(4, "Kết hợp"));
        listType.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listPerson.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        AdapterListTypeFilter paymentAdapter = new AdapterListTypeFilter(listPayment);
        paymentAdapter.setCallback(new AdapterListTypeFilter.Callback() {
            @Override
            public void callbackPositions(List<Payment> selectedPayments) {
                listPaymentCallback = selectedPayments;
            }
        });
        listType.setAdapter(paymentAdapter);


        AdapterListUserFilter adapterListUserFilter = new AdapterListUserFilter(listUser);
        adapterListUserFilter.setCallback(new AdapterListUserFilter.Callback() {
            @Override
            public void callbackPositions(List<User> selectedUsers) {
                listUserCallback = selectedUsers;
            }
        });
        listPerson.setAdapter(adapterListUserFilter);


        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListUserFilter.clearSelections();
                paymentAdapter.clearSelections();
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClick.onCLickFilter(listUserCallback, listPaymentCallback);
                cancel();
            }
        });

    }

    public interface EventClick {
        void onCLickFilter(List<User> selectedUsers, List<Payment> selectedPayments);
    }
}

package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityInfoBillBinding;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.BillInfo;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InfoBillActivity extends AppCompatActivity {
    private ActivityInfoBillBinding binding;
    private Bill bill;
    private BillController billController;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private List<BillInfo> billInfos;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bill = (Bill) getIntent().getSerializableExtra(AppConstant.TABLE_BILL);
        billController = new BillController(this);
        billInfos = new ArrayList<>();
        billInfos = billController.getBillInfoByBillId(bill.getId());
        initToolbar();

        LocalDateTime currentDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(bill.getTime())), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        binding.typePayment.setText(convertStringType(bill.getType()));
        binding.time.setText(formattedDateTime);
        binding.countPerson.setText(bill.getCountPerson() + " người");
        binding.priceAll.setText(decimalFormat.format(bill.getPrice()) + " ₫");
        binding.priceSupperLine.setText(decimalFormat.format(bill.getPriceDiscount()) + " ₫");
        binding.text9.setText("Chi tiết giá cho " + billInfos.size() + " món");

        StringBuilder sbTitle = new StringBuilder();
        for (BillInfo billInfo : billInfos) {
            String output = billInfo.getNameMenu() + " x " + billInfo.getQuantity();
            sbTitle.append(output).append("\n");
        }

        binding.priceAndCount.setText(sbTitle.toString());

    }

    private void initToolbar() {
        binding.toolBar.setTitle("Pub Manager " + bill.getId());
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private String convertStringType(int type) {
        switch (type) {
            case 0:
                return "Tiền mặt";
            case 1:
                return "Chuyển khoản";
            case 2:
                return "Thẻ";
            case 3:
                return "Kết hợp";
            default:
                return "";
        }
    }
}
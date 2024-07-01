package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.BillController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityPayBinding;
import com.main.pubmanagement.model.Bill;
import com.main.pubmanagement.model.OrderDetails;
import com.main.pubmanagement.model.Payment;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.adapter.PaymentAdapter;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity {
    private ActivityPayBinding binding;
    private List<Payment> listPayment;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private List<OrderDetails> listOrderDetails;
    private int countPerson;
    private int payment = 0;
    private BillController billController;
    private int sumPrice;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();

        listOrderDetails = (List<OrderDetails>) getIntent().getSerializableExtra(AppConstant.COLUMN_ORDER_DETAIL_NAME);
        countPerson = getIntent().getIntExtra("countperson", -1);
        billController = new BillController(this);
        listPayment = new ArrayList<>();
        listPayment.add(new Payment(1, "Thanh toán tiền mặt"));
        listPayment.add(new Payment(2, "Chuyển khoản"));
        listPayment.add(new Payment(3, "Thẻ"));
        listPayment.add(new Payment(4, "Kết hợp"));
        binding.listPayment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PaymentAdapter paymentAdapter = new PaymentAdapter(listPayment);
        paymentAdapter.setCallback(new PaymentAdapter.Callback() {
            @Override
            public void callbackPosition(int id) {
                payment = id;
            }
        });
        binding.listPayment.setAdapter(paymentAdapter);


        binding.countProduct.setText(listOrderDetails.size() + " món");
        binding.discount.setText(decimalFormat.format(getTotalDiscountedPrice()) + " ₫");
        int sum = sumTotalWithoutTax() - getTotalDiscountedPrice();
        int sumTax = (int) (sum * 0.1);
        binding.priceNotTax.setText(decimalFormat.format(sum) + " ₫");
        sumPrice = sum + sumTax;
        binding.tax.setText(decimalFormat.format(sumTax) + " ₫");
        binding.sumPrice.setText(decimalFormat.format(sumPrice) + " ₫");


        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTimeMillis = Instant.now().toEpochMilli();
                billController.createBill(new Bill(
                        0, payment, MySharedPreferences.getInstance(PayActivity.this).getInt(AppConstant.COLUMN_USER_ID, 0), sumTotal(), getTotalDiscountedPrice()
                        , String.valueOf(currentTimeMillis), countPerson
                ));
            }
        });
    }

    private void initToolbar() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private int sumTotalWithoutTax() {
        int totalPrice = 0;
        for (OrderDetails o : listOrderDetails
        ) {
            totalPrice += o.getPrice() * o.getQuantity();
        }
        return totalPrice;
    }

    private int sumTotal() {
        int totalPrice = 0;
        for (OrderDetails o : listOrderDetails
        ) {
            totalPrice += o.getPrice() * o.getQuantity();
        }
        return totalPrice + (totalPrice / 10);
    }

    public int getTotalDiscountedPrice() {
        int totalDiscountedPrice = 0;
        for (OrderDetails order : listOrderDetails) {
            int discountedPrice = (int) (order.getPrice() * (order.getDiscount() * 0.01));
            totalDiscountedPrice += discountedPrice * order.getQuantity();
        }
        return totalDiscountedPrice;
    }
}
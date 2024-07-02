package com.main.pubmanagement.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.OderController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityEditBillBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.OrderDetails;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.adapter.OrderDetailAdapter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EditBillActivity extends AppCompatActivity {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private ActivityEditBillBinding binding;
    private OderController oderController;
    private long idOrder;
    private static final int REQUEST_CODE_ADD_MENU = 1;
    private Order order;
    private List<OrderDetails> listOrderDetails;
    private Table table;
    private OrderDetailAdapter orderDetailAdapter;
    private boolean isLayoutVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        table = (Table) getIntent().getExtras().get(AppConstant.TABLE_TABLE_RESTAURANT);


        oderController = new OderController(this);
        listOrderDetails = new ArrayList<>();
        idOrder = getIntent().getLongExtra(AppConstant.COLUMN_ORDER_ID, -1);
        order = oderController.getOrderById(idOrder);
        listOrderDetails = oderController.getListOrderDetailsByIdOrder(idOrder);
        if (table == null) {
            table = oderController.getTableById(order.getIdTable());
        }
        initToolBar();
        double sum = calculateSum(listOrderDetails);
        updateUI(sum);
        binding.countCard.setText("Thực đơn (" + listOrderDetails.size() + " món)");
        binding.countCardPrice.setText(decimalFormat.format(sum) + " ₫");
        binding.sumPerson.setText(order.getPerson() + "");
        binding.sumPrice.setText(decimalFormat.format(sum + (sum * 0.1)) + " ₫");

        orderDetailAdapter = new OrderDetailAdapter();
        orderDetailAdapter.setCallback(new OrderDetailAdapter.Callback() {
            @Override
            public void onClick(int id) {

            }

            @Override
            public void callSum(HashMap<OrderDetails, Integer> cartMap) {
                for (OrderDetails orderDetail : listOrderDetails) {
                    for (Map.Entry<OrderDetails, Integer> entry : cartMap.entrySet()) {
                        if (orderDetail.getId() == entry.getKey().getId()) {
                            orderDetail.setQuantity(entry.getValue());
                        }
                    }
                }
                double sum = calculateSum(listOrderDetails);
                updateUI(sum);
            }
        });
        binding.listProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.listProduct.setAdapter(orderDetailAdapter);
        orderDetailAdapter.setData(listOrderDetails);

        binding.addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditBillActivity.this, AddMenuActivity.class);
                intent.putExtra(AppConstant.COLUMN_ORDER_DETAIL_NAME, (Serializable) listOrderDetails);
                intent.putExtra(AppConstant.COLUMN_ORDER_ID, idOrder);
                startActivityForResult(intent, REQUEST_CODE_ADD_MENU);
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditBillActivity.this, PayActivity.class);
                intent.putExtra(AppConstant.COLUMN_ORDER_DETAIL_NAME, (Serializable) listOrderDetails);
                intent.putExtra("countperson", order.getPerson());
                intent.putExtra(AppConstant.COLUMN_ORDER_ID, order.getId());
                intent.putExtra(AppConstant.COLUMN_RESTAURANT_ID, order.getIdTable());
                startActivity(intent);
            }
        });
        binding.sumPerson.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int inputValue = Integer.parseInt(s.toString());
                    if (inputValue > 0 && inputValue < 15) {
                        binding.sumit.setEnabled(true);
                        binding.sumit.setAlpha(1);
                        oderController.updateOrderPerson(Integer.parseInt(String.valueOf(idOrder)), inputValue);
                    } else {
                        binding.sumit.setEnabled(false);
                        binding.sumit.setAlpha(0.4f);
                    }
                } catch (NumberFormatException e) {
                    binding.sumit.setEnabled(false);
                    binding.sumit.setAlpha(0.4f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.listProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && isLayoutVisible) {
                    hideLayout();
                } else if (dy < 0 && !isLayoutVisible) {
                    showLayout();
                }
            }
        });
    }

    private void initToolBar() {
        binding.toolBar.setTitle("Bàn " + table.getName() + " " + table.getNameStorey());
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private float convertIntToFloat(int discount) {
        if (discount == 0) {
            return 0;
        } else {
            return (float) (discount * 0.01);
        }
    }

    private double calculateSum(List<OrderDetails> orderDetailsList) {
        double sum = 0;
        for (OrderDetails orderDetail : orderDetailsList) {
            sum += ((orderDetail.getPrice() * orderDetail.getQuantity()) - (orderDetail.getPrice() * convertIntToFloat(orderDetail.getDiscount())));
        }
        return sum;
    }

    private void updateUI(double sum) {
        binding.countCard.setText("Thực đơn (" + listOrderDetails.size() + " món)");
        binding.countCardPrice.setText(decimalFormat.format(sum) + " ₫");
        binding.sumPerson.setText(order.getPerson() + "");
        binding.sumPrice.setText(decimalFormat.format(sum + (sum * 0.1)) + " ₫");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_MENU && resultCode == RESULT_OK && data != null) {
            listOrderDetails.clear();
            HashMap<Menu, Integer> cartMap = (HashMap<Menu, Integer>) data.getSerializableExtra("cartMap");
            for (Map.Entry<Menu, Integer> entry : cartMap.entrySet()) {
                Menu menu = entry.getKey();
                int quantity = entry.getValue();
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setId(menu.getId());
                orderDetail.setName(menu.getName());
                orderDetail.setPrice(menu.getPrice());
                orderDetail.setQuantity(quantity);
                orderDetail.setIdMenu(menu.getId());
                listOrderDetails.add(orderDetail);
            }
            orderDetailAdapter.setData(listOrderDetails);
            double newSum = calculateSum(listOrderDetails);
            updateUI(newSum);
        }
    }

    private void hideLayout() {
        binding.layoutAddBill.animate()
                .translationY(binding.layoutAddBill.getHeight())
                .alpha(0.0f)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        binding.layoutAddBill.setVisibility(View.GONE);
                        binding.addMenu.setVisibility(View.GONE);
                        isLayoutVisible = false;
                    }
                });
    }

    private void showLayout() {
        binding.layoutAddBill.setVisibility(View.VISIBLE);
        binding.addMenu.setVisibility(View.VISIBLE);
        binding.layoutAddBill.setAlpha(0.0f);
        binding.layoutAddBill.animate()
                .translationY(0)
                .alpha(1.0f)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        isLayoutVisible = true;
                    }
                });
    }
}
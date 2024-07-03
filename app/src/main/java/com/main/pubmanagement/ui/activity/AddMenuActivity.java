package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuController;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.controller.OderController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityAddMenuBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.Order;
import com.main.pubmanagement.model.OrderDetails;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.adapter.MenuAdapter;
import com.main.pubmanagement.ui.adapter.StoreyAdapter;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddMenuActivity extends AppCompatActivity {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private ActivityAddMenuBinding binding;
    private Table table;
    private StoreyAdapter storeyAdapter;
    private MenuTypeController menuTypeController;
    private MenuController menuController;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;
    private HashMap<Menu, Integer> cartMap;
    private OderController oderController;
    private List<OrderDetails> listOrderDetails;
    private long idOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolBar();
        menuList = new ArrayList<>();
        listOrderDetails = new ArrayList<>();
        menuTypeController = new MenuTypeController(this);
        menuController = new MenuController(this);
        oderController = new OderController(this);
        menuList = menuController.getListMenu();
        listOrderDetails = (List<OrderDetails>) getIntent().getSerializableExtra(AppConstant.COLUMN_ORDER_DETAIL_NAME);
        idOrder = getIntent().getLongExtra(AppConstant.COLUMN_ORDER_ID, -1);

        storeyAdapter = new StoreyAdapter(menuTypeController.getListMenuType(), new StoreyAdapter.Callback() {
            @Override
            public void callbackCLick(int position) {
                loadData(position);
            }
        });
        binding.listCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.listCategory.setAdapter(storeyAdapter);

        menuAdapter = new MenuAdapter();
        menuAdapter.setCallback(new MenuAdapter.Callback() {
            @Override
            public void onClick(int id) {

            }

            @Override
            public void callSum(HashMap<Menu, Integer> cartMap) {
                binding.layoutAddBill.setVisibility(cartMap.size() > 0 ? View.VISIBLE : View.GONE);
                binding.countCard.setText("Thực đơn (" + cartMap.size() + " món)");
                double sum = 0;
                Set<Map.Entry<Menu, Integer>> entries = cartMap.entrySet();
                for (Map.Entry<Menu, Integer> entry : entries) {
                    Menu key = entry.getKey();
                    Integer value = entry.getValue();
                    sum += ((key.getPrice() * value) - (key.getPrice() * convertIntToFloat(key.getDiscount())));
                }
                AddMenuActivity.this.cartMap = cartMap;
                binding.countCardPrice.setText(decimalFormat.format(sum) + " ₫");
                binding.sumPrice.setText(decimalFormat.format(sum + (sum * 0.1)) + " ₫");
            }
        });

        binding.listProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.listProduct.setAdapter(menuAdapter);

        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAdapter.clear();
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeOrderDetail();
                addOrderDetail();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("cartMap", cartMap);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void initToolBar() {
        binding.toolBar.setTitle("Chọn Món");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void iniDataListAll() {
        menuAdapter.setData(menuList);
        menuAdapter.setDataOrderDetail(listOrderDetails);
    }

    private void iniDataListById(int id) {
        List<Menu> menuListSoft = new ArrayList<>();
        for (Menu menu : menuList
        ) {
            if (menu.getIdMenuType() == id) {
                menuListSoft.add(menu);
            }
        }
        menuAdapter.setData(menuListSoft);
    }

    private void loadData(int position) {
        if (position == 0) {
            iniDataListAll();
        } else {
            iniDataListById(position);
        }
    }

    private float convertIntToFloat(int discount) {
        if (discount == 0) {
            return 0;
        } else {
            return (float) (discount * 0.01);
        }
    }

    private void addOrderDetail() {
        Set<Map.Entry<Menu, Integer>> entries = cartMap.entrySet();
        for (Map.Entry<Menu, Integer> entry : entries) {
            Menu key = entry.getKey();
            Integer value = entry.getValue();
            oderController.createOrderDetail(new OrderDetails(
                    0,
                    Integer.parseInt(String.valueOf(idOrder)),
                    value,
                    key.getDiscount(),
                    key.getPrice(),
                    key.getName(),
                    key.getId()
            ));

            for (OrderDetails order : listOrderDetails
            ) {
                if (key.getId() == order.getIdMenu()) {
                    if (value > order.getQuantity()) {
                        menuController.updateMenuCount(key.getId(), key.getCount() - (value - order.getQuantity()));
                    } else {
                        menuController.updateMenuCount(key.getId(), key.getCount() + (order.getQuantity() - value));
                    }
                }
            }
        }
    }

    private void removeOrderDetail() {
        oderController.removeOrderDetailByIdOrder(Integer.parseInt(String.valueOf(idOrder)));
    }
}
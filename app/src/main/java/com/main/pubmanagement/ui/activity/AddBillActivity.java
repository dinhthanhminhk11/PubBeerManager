package com.main.pubmanagement.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuController;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.controller.OderController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
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

public class AddBillActivity extends AppCompatActivity {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private ActivityAddBillBinding binding;
    private Table table;
    private StoreyAdapter storeyAdapter;
    private boolean isLayoutVisible = true;
    private MenuTypeController menuTypeController;
    private MenuController menuController;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;
    private HashMap<Menu, Integer> cartMap;
    private OderController oderController;
    private int finalSum = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        table = (Table) getIntent().getExtras().get(AppConstant.TABLE_TABLE_RESTAURANT);
        initToolBar();
        menuList = new ArrayList<>();
        menuTypeController = new MenuTypeController(this);
        menuController = new MenuController(this);
        oderController = new OderController(this);
        menuList = menuController.getListMenu();
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
                finalSum = (int) (sum + (sum * 0.1));
                AddBillActivity.this.cartMap = cartMap;
                binding.countCardPrice.setText(decimalFormat.format(sum) + " ₫");
                binding.sumPrice.setText(decimalFormat.format(sum + (sum * 0.1)) + " ₫");
            }
        });
        binding.listProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.listProduct.setAdapter(menuAdapter);

        binding.sumit.setEnabled(false);
        binding.sumit.setAlpha(0.4f);
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

        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAdapter.clear();
                binding.sumPerson.setText("");
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTimeMillis = Instant.now().toEpochMilli();

                long result = oderController.create(new Order(
                        0,
                        MySharedPreferences.getInstance(AddBillActivity.this).getInt(AppConstant.COLUMN_USER_ID, 0),
                        table.getId(),
                        0,
                        String.valueOf(currentTimeMillis),
                        finalSum,
                        Integer.parseInt(binding.sumPerson.getText().toString())
                ));
                if (result > 0) {
                    Set<Map.Entry<Menu, Integer>> entries = cartMap.entrySet();
                    for (Map.Entry<Menu, Integer> entry : entries) {
                        Menu key = entry.getKey();
                        Integer value = entry.getValue();
                        oderController.createOrderDetail(new OrderDetails(
                                0,
                                Integer.parseInt(String.valueOf(result)),
                                value,
                                key.getDiscount(),
                                key.getPrice(),
                                key.getName(),
                                key.getId()
                        ));
                    }
                    Toast.makeText(AddBillActivity.this, "Order Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBillActivity.this, EditBillActivity.class);
                    intent.putExtra(AppConstant.COLUMN_ORDER_ID, result);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddBillActivity.this, "Order Thất Bại", Toast.LENGTH_SHORT).show();

                }
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

    private void iniDataListAll() {
        menuAdapter.setData(menuList);
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

    private void hideLayout() {
        binding.layoutAddBill.animate()
                .translationY(binding.layoutAddBill.getHeight())
                .alpha(0.0f)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        binding.layoutAddBill.setVisibility(View.GONE);
                        isLayoutVisible = false;
                    }
                });
    }

    private void showLayout() {
        binding.layoutAddBill.setVisibility(View.VISIBLE);
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
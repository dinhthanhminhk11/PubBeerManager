package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.controller.TableController;
import com.main.pubmanagement.databinding.ActivityAddTableBinding;
import com.main.pubmanagement.model.Storey;

public class AddTableActivity extends AppCompatActivity {

    private ActivityAddTableBinding binding;
    private TableController tableController;
    private Storey storey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        tableController = new TableController(this);
        storey = (Storey) getIntent().getSerializableExtra(AppConstant.TABLE_STOREY);
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (tableController.create(binding.name.getText().toString(), Integer.parseInt(binding.price.getText().toString()) , storey.getId()) > 0) {
                        Toast.makeText(AddTableActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddTableActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initToolbar() {
        binding.toolBar.setTitle("Thêm bàn mới");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean isValid() {
        String name = binding.name.getText().toString();
        String priceText = binding.price.getText().toString();

        if (TextUtils.isEmpty(name)) {
            binding.name.setError("Tên không được để trống");
            return false;
        }

        if (TextUtils.isEmpty(priceText)) {
            binding.price.setError("Số ghế không được để trống");
            return false;
        }

        int price;
        try {
            price = Integer.parseInt(priceText);
        } catch (NumberFormatException e) {
            binding.price.setError("Số ghế phải là số hợp lệ");
            return false;
        }

        if (price < 4 || price > 20) {
            binding.price.setError("Số ghế phải lớn hơn 4 và nhỏ hơn 20");
            return false;
        }

        return true;
    }
}
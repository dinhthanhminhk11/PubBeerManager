package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.databinding.ActivityAddStoreyBinding;
import com.main.pubmanagement.model.MenuType;

public class AddStoreyActivity extends AppCompatActivity {
    private ActivityAddStoreyBinding binding;
    private StoreyController storeyController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStoreyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        storeyController = new StoreyController(this);
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (storeyController.create(binding.edPhone.getText().toString()) > 0) {
                        Toast.makeText(AddStoreyActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddStoreyActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initToolbar() {
        binding.toolBar.setTitle("Thêm tầng/khu vực mới");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean isValid() {
        if (TextUtils.isEmpty(binding.edPhone.getText().toString())) {
            binding.edPhone.setError("Không được để trống");
            return false;
        }
        return true;
    }
}
package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.databinding.ActivityEditNameStoreyBinding;
import com.main.pubmanagement.model.Storey;

public class EditNameStoreyActivity extends AppCompatActivity {
    private StoreyController storeyController;
    private ActivityEditNameStoreyBinding binding;
    private Storey storey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNameStoreyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        storeyController = new StoreyController(this);
        storey = (Storey) getIntent().getSerializableExtra(AppConstant.TABLE_STOREY);
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (storeyController.update(storey.getId(), binding.edPhone.getText().toString()) > 0) {
                        Toast.makeText(EditNameStoreyActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditNameStoreyActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.edPhone.setText(storey.getName());
    }

    private void initToolbar() {
        binding.toolBar.setTitle("Sửa tầng/khu vực mới");
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
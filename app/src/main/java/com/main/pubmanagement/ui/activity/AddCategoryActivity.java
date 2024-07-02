package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.databinding.ActivityAddCategoryBinding;
import com.main.pubmanagement.model.MenuType;

public class AddCategoryActivity extends AppCompatActivity {

    private ActivityAddCategoryBinding binding;
    private MenuTypeController menuTypeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        menuTypeController = new MenuTypeController(this);
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuTypeController.create(new MenuType(0, binding.edPhone.getText().toString())) > 0) {
                    Toast.makeText(AddCategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(AddCategoryActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initToolbar() {
        binding.toolBar.setTitle("Thêm loại mới");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
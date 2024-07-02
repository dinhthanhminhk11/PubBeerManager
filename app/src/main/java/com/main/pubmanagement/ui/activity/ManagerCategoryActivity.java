package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.databinding.ActivityManagerCategoryBinding;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.ui.adapter.CategoryAdapter;

public class ManagerCategoryActivity extends AppCompatActivity {

    private MenuTypeController menuTypeController;
    private ActivityManagerCategoryBinding binding;
    private CategoryAdapter categoryAdapter;
    private int position = 0;
    private MenuType menuType;
    private MenuType menuType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagerCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        menuTypeController = new MenuTypeController(this);
        initToolbar();
        menuType2 = (MenuType) getIntent().getSerializableExtra(AppConstant.TABLE_MENU_TYPE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(new CategoryAdapter.Callback() {
            @Override
            public void callbackCLick(MenuType menuType) {
                ManagerCategoryActivity.this.menuType = menuType;
            }
        });
        binding.recyclerView.setAdapter(categoryAdapter);




        binding.addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerCategoryActivity.this, AddCategoryActivity.class));
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Product_fragment", menuType);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryAdapter.setData(menuTypeController.getListMenuType());
        if (menuType2 != null) {
            categoryAdapter.selectItem(menuType2.getId());
        }
    }

    private void initToolbar() {
        binding.toolBar.setTitle("Loại menu");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityLoginBinding;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.adapter.StoreyAdapter;

public class AddBillActivity extends AppCompatActivity {

    private ActivityAddBillBinding binding;
    private Table table;
    private StoreyAdapter storeyAdapter;
    private MenuTypeController menuTypeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        table = (Table) getIntent().getExtras().get(AppConstant.TABLE_TABLE_RESTAURANT);
        initToolBar();

        menuTypeController = new MenuTypeController(this);
        storeyAdapter = new StoreyAdapter(menuTypeController.getListMenuType(), new StoreyAdapter.Callback() {
            @Override
            public void callbackCLick(int position) {
//                if (position == 0) {
//                    iniDataListAll();
//                } else {
//                    iniDataListById(position);
//                }
            }
        });
        binding.listCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.listCategory.setAdapter(storeyAdapter);

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
}
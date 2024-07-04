package com.main.pubmanagement.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.controller.TableController;
import com.main.pubmanagement.databinding.ActivityInfoStoreyBinding;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.adapter.TableAdapter;
import com.main.pubmanagement.ui.view.dialog.DialogConfirmCustom;

import java.util.ArrayList;
import java.util.List;

public class InfoStoreyActivity extends AppCompatActivity {
    private ActivityInfoStoreyBinding binding;
    private TableController tableController;
    private StoreyController storeyController;
    private TableAdapter tableAdapter;
    private List<Table> tableList;
    private Storey storey;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoStoreyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tableList = new ArrayList<>();
        tableController = new TableController(this);
        storeyController = new StoreyController(this);
        storey = (Storey) getIntent().getSerializableExtra(AppConstant.TABLE_STOREY);
        initToolbar();
        tableAdapter = new TableAdapter(new TableAdapter.CallBack() {
            @Override
            public void onCLick(Table table) {
                Intent intent = new Intent(InfoStoreyActivity.this, EditInFoTableActivity.class);
                intent.putExtra(AppConstant.TABLE_TABLE_RESTAURANT, table);
                startActivity(intent);
            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(InfoStoreyActivity.this, 2));
        binding.recyclerView.setAdapter(tableAdapter);

        binding.addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoStoreyActivity.this, AddTableActivity.class);
                intent.putExtra(AppConstant.TABLE_STOREY, storey);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tableList = tableController.getListTableByIdStorey(storey.getId());
        iniDataListAll();
    }

    private void iniDataListAll() {
        tableAdapter.setData(tableList);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle(storey.getName());
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemdelete_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iconDelete) {
            showDialogLogOut();
            return true;
        } else if (id == R.id.iconEdit) {
            Intent intent = new Intent(InfoStoreyActivity.this, EditNameStoreyActivity.class);
            intent.putExtra(AppConstant.TABLE_STOREY, storey);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogLogOut() {
        DialogConfirmCustom dialog = DialogConfirmCustom.create(
                this,
                "Bạn có chắc chắn muốn xóa tầng/khu vực này",
                "Đồng ý", "Hủy",
                () -> {
                    storeyController.deleteAllTable(storey.getId());
                    storeyController.delete(storey.getId());
                    Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
        );
        dialog.show();
    }

}
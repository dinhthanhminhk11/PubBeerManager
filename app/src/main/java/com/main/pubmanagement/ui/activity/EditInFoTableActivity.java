package com.main.pubmanagement.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.controller.TableController;
import com.main.pubmanagement.databinding.ActivityAddStoreyBinding;
import com.main.pubmanagement.databinding.ActivityEditInFoTableBinding;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.view.dialog.DialogConfirmCustom;

public class EditInFoTableActivity extends AppCompatActivity {
    private ActivityEditInFoTableBinding binding;
    private TableController tableController;
    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInFoTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        tableController = new TableController(this);
        table = (Table) getIntent().getSerializableExtra(AppConstant.TABLE_TABLE_RESTAURANT);

        binding.name.setText(table.getName());
        binding.price.setText(table.getCountChair() + "");

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (tableController.update(table.getId(), binding.name.getText().toString(), Integer.parseInt(binding.price.getText().toString())) > 0) {
                        Toast.makeText(EditInFoTableActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditInFoTableActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void initToolbar() {
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Chỉnh sửa bàn");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemdelete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iconDelete) {
            showDialogLogOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogLogOut() {
        DialogConfirmCustom dialog = DialogConfirmCustom.create(
                this,
                "Bạn có chắc chắn muốn xóa bàn này",
                "Đồng ý", "Hủy",
                () -> {
                    tableController.delete(table.getId());
                    Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
        );
        dialog.show();
    }
}
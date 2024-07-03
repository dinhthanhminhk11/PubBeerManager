package com.main.pubmanagement.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.ActivityAddMemberBinding;
import com.main.pubmanagement.databinding.ActivityEditMemberBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.customview.spinner.NiceSpinner;
import com.main.pubmanagement.ui.customview.spinner.OnSpinnerItemSelectedListener;
import com.main.pubmanagement.ui.view.dialog.DialogConfirmCustom;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EditMemberActivity extends AppCompatActivity {
    private ActivityEditMemberBinding binding;
    private String nameShift = "Cả ngày";
    private UserController userController;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private MenuItem menuItem;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();

        user = (User) getIntent().getSerializableExtra(AppConstant.TABLE_USER);

        userController = new UserController(this);
        List<String> dataUnit = new LinkedList<>(Arrays.asList(
                "Cả ngày",
                "Ca Sáng",
                "Ca Chiều",
                "Ca Tối"
        ));
        nameShift = convertShiftToText(user.getShift());
        binding.salary.addTextChangedListener(new NumberTextWatcher(binding.salary));

        binding.shift.attachDataSource(dataUnit);
        binding.shift.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                nameShift = (String) parent.getItemAtPosition(position);
            }
        });


        binding.shift.setSelectedIndex(user.getShift());
        binding.cardId.setText(user.getCardId());
        binding.name.setText(user.getName());
        binding.username.setText(user.getUsername());
        binding.password.setText(user.getPassword());
        binding.phone.setText(user.getPhone());
        binding.salary.setText(decimalFormat.format(user.getSalary()));
        binding.sumit.setText("Lưu");
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userController.editMember(new User(
                        user.getId(),
                        binding.name.getText().toString(),
                        binding.phone.getText().toString(),
                        binding.cardId.getText().toString(),
                        binding.username.getText().toString(),
                        binding.password.getText().toString(),
                        1,
                        convertUnitToNumber(nameShift),
                        Integer.parseInt(binding.salary.getText().toString().replaceAll(AppConstant.DOT, ""))
                )) > 0) {
                    Toast.makeText(EditMemberActivity.this, "Sửa Nhân viên thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditMemberActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Chỉnh sửa nhân viên");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public int convertUnitToNumber(String unit) {
        if (unit.equalsIgnoreCase("Cả ngày")) {
            return 0;
        } else if (unit.equalsIgnoreCase("Ca Sáng")) {
            return 1;
        } else if (unit.equalsIgnoreCase("Ca Chiều")) {
            return 2;
        } else if (unit.equalsIgnoreCase("Ca Tối")) {
            return 3;
        } else {
            throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    public String convertShiftToText(int shift) {
        switch (shift) {
            case 0:
                return "Cả ngày";
            case 1:
                return "Ca Sáng";
            case 2:
                return "Ca Chiều";
            case 3:
                return "Ca Tối";
            default:
                return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemdelete, menu);
        menuItem = menu.findItem(R.id.iconDelete);
        menuItem.setIcon(R.drawable.baseline_delete_24);
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
                "Bạn có chắc chắn muốn xóa nhân sự này",
                "Đồng ý", "Hủy",
                () -> {
                    userController.deleteUserById(user.getId());
                    Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
        );
        dialog.show();
    }


    class NumberTextWatcher implements TextWatcher {
        private DecimalFormat df;
        private DecimalFormat dfnd;
        private boolean hasFractionalPart;
        private EditText et;

        public NumberTextWatcher(EditText et) {
            df = new DecimalFormat("#,###.##");
            df.setDecimalSeparatorAlwaysShown(true);
            dfnd = new DecimalFormat("#,###");
            this.et = et;
            hasFractionalPart = false;
        }

        @SuppressWarnings("unused")
        private static final String TAG = "NumberTextWatcher";

        public void afterTextChanged(Editable s) {
            et.removeTextChangedListener(this);
            try {
                int inilen, endlen;
                inilen = et.getText().length();
                String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
                Number n = df.parse(v);
                int cp = et.getSelectionStart();
                if (hasFractionalPart) {
                    et.setText(df.format(n));
                } else {
                    et.setText(dfnd.format(n));
                }
                endlen = et.getText().length();
                int sel = (cp + (endlen - inilen));
                if (sel > 0 && sel <= et.getText().length()) {
                    et.setSelection(sel);
                } else {
                    et.setSelection(et.getText().length() - 1);
                }
            } catch (NumberFormatException nfe) {
            } catch (ParseException e) {
            }

            et.addTextChangedListener(this);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()))) {
                hasFractionalPart = true;
            } else {
                hasFractionalPart = false;
            }
        }
    }
}
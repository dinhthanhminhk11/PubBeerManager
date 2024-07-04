package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.ActivityAddMemberBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.customview.spinner.NiceSpinner;
import com.main.pubmanagement.ui.customview.spinner.OnSpinnerItemSelectedListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddMemberActivity extends AppCompatActivity {
    private ActivityAddMemberBinding binding;
    private String nameShift = "Cả ngày";
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        userController = new UserController(this);
        List<String> dataUnit = new LinkedList<>(Arrays.asList(
                "Cả ngày",
                "Ca Sáng",
                "Ca Chiều",
                "Ca Tối"
        ));
        binding.salary.addTextChangedListener(new NumberTextWatcher(binding.salary));

        binding.shift.attachDataSource(dataUnit);
        binding.shift.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                nameShift = (String) parent.getItemAtPosition(position);
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    if (userController.register(new User(
                            binding.name.getText().toString(),
                            binding.phone.getText().toString(),
                            binding.cardId.getText().toString(),
                            binding.username.getText().toString(),
                            binding.password.getText().toString(),
                            1,
                            convertUnitToNumber(nameShift),
                            Integer.parseInt(binding.salary.getText().toString().replaceAll(AppConstant.DOT, ""))
                    )) > 0) {
                        Toast.makeText(AddMemberActivity.this, "Thêm Nhân viên thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddMemberActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void initToolbar() {
        binding.toolBar.setTitle("Thêm mới nhân viên");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static int convertUnitToNumber(String unit) {
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

    private boolean isValid() {
        if (TextUtils.isEmpty(binding.username.getText().toString())) {
            binding.username.setError("Tài khoản không được để trống");
            return false;
        } else if (binding.username.getText().toString().length() < 8 || binding.username.getText().toString().length() > 15) {
            binding.username.setError("Tài khoản phải lớn hơn 8 kí tự và bé hơn 15 kí tự");
            return false;
        }
        if (TextUtils.isEmpty(binding.name.getText().toString())) {
            binding.name.setError("Tên không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(binding.cardId.getText().toString())) {
            binding.cardId.setError("Cccd không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(binding.phone.getText().toString())) {
            binding.phone.setError("Số điện thoại không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(binding.password.getText().toString())) {
            binding.password.setError("Mật khẩu không được để trống");
            return false;
        } else if (binding.password.getText().toString().length() < 8 || binding.password.getText().toString().length() > 15) {
            binding.password.setError("Mật khẩu phải lớn hơn 8 kí tự và bé hơn 15 kí tự");
            return false;
        }
        if (TextUtils.isEmpty(binding.salary.getText().toString())) {
            binding.salary.setError("Tiền lương không đc để trống");
            return false;
        }
        return true;
    }

}
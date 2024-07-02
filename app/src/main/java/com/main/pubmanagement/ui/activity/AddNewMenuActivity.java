package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.databinding.ActivityAddNewMenuBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.ui.customview.spinner.NiceSpinner;
import com.main.pubmanagement.ui.customview.spinner.OnSpinnerItemSelectedListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddNewMenuActivity extends AppCompatActivity {
    private ActivityAddNewMenuBinding binding;
    private MenuTypeController menuTypeController;
    private String nameUnit = "Tháp";
    private int idMenuType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        menuTypeController = new MenuTypeController(this);

        List<Storey> list = menuTypeController.getListMenuTypeNoAll();
        binding.listCategory.attachDataSource(list);
        binding.listCategory.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                Storey sr = (Storey) parent.getItemAtPosition(position);
                idMenuType = sr.getId();
            }
        });

        List<String> dataUnit = new LinkedList<>(Arrays.asList(
                "Tháp",
                "Đĩa",
                "Con",
                "Nồi",
                "Kg",
                "Suất",
                "Bát tô",
                "Ly",
                "Chai",
                "Lon"
        ));

        binding.listUnit.attachDataSource(dataUnit);
        binding.listUnit.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                nameUnit = (String) parent.getItemAtPosition(position);
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuTypeController.createMenu(new Menu(
                        binding.name.getText().toString(),
                        Integer.parseInt(binding.price.getText().toString().replaceAll(AppConstant.DOT, "")),
                        convertUnitToNumber(nameUnit),
                        idMenuType,
                        Integer.parseInt(binding.priceDiscount.getText().toString()),
                        binding.content.getText().toString().equals("") ? null : binding.content.getText().toString(),
                        Integer.parseInt(binding.count.getText().toString())
                )) > 0) {
                    Toast.makeText(AddNewMenuActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Another_Result_Key", "add_success");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(AddNewMenuActivity.this, "tHEM THAT BAI", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.price.addTextChangedListener(new NumberTextWatcher(binding.price));

    }

    private void initToolbar() {
        binding.toolBar.setTitle("Thêm món ăn");
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static int convertUnitToNumber(String unit) {
        if (unit.equalsIgnoreCase("tháp")) {
            return 0;
        } else if (unit.equalsIgnoreCase("đĩa")) {
            return 1;
        } else if (unit.equalsIgnoreCase("con")) {
            return 2;
        } else if (unit.equalsIgnoreCase("nồi")) {
            return 3;
        } else if (unit.equalsIgnoreCase("kg")) {
            return 4;
        } else if (unit.equalsIgnoreCase("suất")) {
            return 5;
        } else if (unit.equalsIgnoreCase("bát tô")) {
            return 6;
        } else if (unit.equalsIgnoreCase("ly")) {
            return 7;
        } else if (unit.equalsIgnoreCase("chai")) {
            return 8;
        } else if (unit.equalsIgnoreCase("lon")) {
            return 9;
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
}
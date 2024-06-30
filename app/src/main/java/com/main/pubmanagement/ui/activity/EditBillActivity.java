package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ActivityAddBillBinding;
import com.main.pubmanagement.databinding.ActivityEditBillBinding;

public class EditBillActivity extends AppCompatActivity {

    private ActivityEditBillBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
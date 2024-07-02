package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ActivityAddMemberBinding;

public class AddMemberActivity extends AppCompatActivity {
    private ActivityAddMemberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
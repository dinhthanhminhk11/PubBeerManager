package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.main.pubmanagement.MainActivity;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.ActivityLoginBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;

public class LoginActivity extends AppCompatActivity {
    private UserController userController;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userController = new UserController(this);

//        binding.edEmail.setText("staffdinhminh");
        binding.edEmail.setText("dinhthanhminh");
        binding.edPass.setText("123456");
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userController.login(binding.edEmail.getText().toString(), binding.edPass.getText().toString());
                if (user != null) {
                    MySharedPreferences.getInstance(LoginActivity.this).putString(AppConstant.COLUMN_USER_USERNAME, binding.edEmail.getText().toString());
                    MySharedPreferences.getInstance(LoginActivity.this).putString(AppConstant.COLUMN_USER_PASSWORD, binding.edPass.getText().toString());
                    MySharedPreferences.getInstance(LoginActivity.this).putInt(AppConstant.COLUMN_USER_ROLE, user.getRole());
                    MySharedPreferences.getInstance(LoginActivity.this).putInt(AppConstant.COLUMN_USER_ID, user.getId());
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Hãy liên hệ với quản lý để lấy lại mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
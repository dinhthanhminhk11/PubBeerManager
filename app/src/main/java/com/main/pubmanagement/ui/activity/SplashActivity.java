package com.main.pubmanagement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.main.pubmanagement.MainActivity;
import com.main.pubmanagement.R;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;

public class SplashActivity extends AppCompatActivity {

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userController = new UserController(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (MySharedPreferences.getInstance(SplashActivity.this).getString(AppConstant.COLUMN_USER_USERNAME, "").equals("")) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
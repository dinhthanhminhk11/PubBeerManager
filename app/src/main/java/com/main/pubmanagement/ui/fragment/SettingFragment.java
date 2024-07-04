package com.main.pubmanagement.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.FragmentSettingBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.activity.EditMemberActivity;
import com.main.pubmanagement.ui.customview.spinner.NiceSpinner;
import com.main.pubmanagement.ui.customview.spinner.OnSpinnerItemSelectedListener;
import com.main.pubmanagement.ui.view.dialog.DialogConfirmCustom;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SettingFragment extends BaseFragment<FragmentSettingBinding> {
    private UserController userController;
    private User user;

    @Override
    protected FragmentSettingBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSettingBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userController = new UserController(getActivity());

        user = userController.getUserById(MySharedPreferences.getInstance(getActivity()).getInt(AppConstant.COLUMN_USER_ID, 0));

        binding.cardId.setText(user.getCardId());
        binding.name.setText(user.getName());
        binding.username.setText(user.getUsername());
        binding.password.setText(user.getPassword());
        binding.phone.setText(user.getPhone());
        binding.sumit.setText("Lưu");

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid(
                        binding.username.getText().toString(),
                        binding.password.getText().toString(),
                        binding.name.getText().toString(),
                        binding.cardId.getText().toString(),
                        binding.phone.getText().toString()
                )) {
                    showDialogLogOut();
                }
            }
        });
    }

    private void showDialogLogOut() {
        DialogConfirmCustom dialog = DialogConfirmCustom.create(
                getActivity(),
                "Bạn có chắc chắn muốn thay đổi thông tin",
                "Đồng ý", "Hủy",
                () -> {
                    if (userController.updateProfile(new User(
                            user.getId(),
                            binding.name.getText().toString(),
                            binding.phone.getText().toString(),
                            binding.cardId.getText().toString(),
                            binding.username.getText().toString(),
                            binding.password.getText().toString()
                    )) > 0) {
                        Toast.makeText(getActivity(), "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        dialog.show();
    }

    private boolean isValid(String username, String password, String name, String cardId, String phone) {
        if (TextUtils.isEmpty(username)) {
            binding.username.setError("Tài khoản không được để trống");
            return false;
        } else if (username.length() < 8 || username.length() > 15) {
            binding.username.setError("Tài khoản phải lớn hơn 8 kí tự và bé hơn 15 kí tự");
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            binding.name.setError("Tên không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(cardId)) {
            binding.cardId.setError("Cccd không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            binding.phone.setError("Số điện thoại không đc để trống");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            binding.password.setError("Mật khẩu không được để trống");
            return false;
        } else if (password.length() < 8 || password.length() > 15) {
            binding.password.setError("Mật khẩu phải lớn hơn 8 kí tự và bé hơn 15 kí tự");
            return false;
        }

        return true;
    }
}
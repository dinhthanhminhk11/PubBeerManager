package com.main.pubmanagement.ui.fragment.manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.controller.UserController;
import com.main.pubmanagement.databinding.FragmentFundNumberBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.activity.AddMemberActivity;
import com.main.pubmanagement.ui.activity.AddMenuActivity;
import com.main.pubmanagement.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FundNumberFragment extends BaseFragment<FragmentFundNumberBinding> {
    private List<User> filteredList;
    private UserAdapter userAdapter;
    private UserController userController;
    private List<User> list;

    @Override
    protected FragmentFundNumberBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFundNumberBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userController = new UserController(getActivity());
        userAdapter = new UserAdapter();
        list = new ArrayList<>();
        filteredList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(userAdapter);
        binding.addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddMemberActivity.class));
            }
        });

        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list = userController.getUsersWithRoleOne();
        Collections.reverse(list);
        userAdapter.setData(list);

        if(!binding.search.getText().toString().equals("")){
            filter(binding.search.getText().toString());
        }
    }

    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(list);
        } else {
            for (User user : list) {
                if (user.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(user);
                }
            }
        }
        userAdapter.setData(filteredList);
    }
}
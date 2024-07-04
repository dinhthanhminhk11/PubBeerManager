package com.main.pubmanagement.ui.fragment.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuTypeController;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.databinding.FragmentProductBinding;
import com.main.pubmanagement.databinding.FragmentStoreyBinding;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.activity.AddStoreyActivity;
import com.main.pubmanagement.ui.activity.InfoStoreyActivity;
import com.main.pubmanagement.ui.adapter.StoreyManagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StoreyFragment extends BaseFragment<FragmentStoreyBinding> {
    private MenuTypeController menuTypeController;
    private StoreyManagerAdapter storeyManagerAdapter;
    private StoreyController storeyController;
    private List<Storey> list;
    private List<Storey> filteredList;

    @Override
    protected FragmentStoreyBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentStoreyBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuTypeController = new MenuTypeController(getActivity());
        storeyController = new StoreyController(getActivity());
        list = new ArrayList<>();
        filteredList = new ArrayList<>();
        storeyManagerAdapter = new StoreyManagerAdapter(new StoreyManagerAdapter.Callback() {
            @Override
            public void callbackCLick(Storey so) {
                Intent intent = new Intent(getActivity(), InfoStoreyActivity.class);
                intent.putExtra(AppConstant.TABLE_STOREY, so);
                startActivity(intent);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(storeyManagerAdapter);

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

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddStoreyActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list = storeyController.getListStoreyNotAll();
        Collections.reverse(list);
        storeyManagerAdapter.setData(list);
        if (!binding.search.getText().toString().equals("")) {
            filter(binding.search.getText().toString());
        }
    }

    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(list);
        } else {
            for (Storey user : list) {
                if (user.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(user);
                }
            }
        }
        storeyManagerAdapter.setData(filteredList);
    }
}
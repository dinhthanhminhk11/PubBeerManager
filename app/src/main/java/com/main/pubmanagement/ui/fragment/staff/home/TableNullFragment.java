package com.main.pubmanagement.ui.fragment.staff.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.TableController;
import com.main.pubmanagement.databinding.FragmentTableNullBinding;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.activity.AddBillActivity;
import com.main.pubmanagement.ui.adapter.TableAdapter;


public class TableNullFragment extends BaseFragment<FragmentTableNullBinding> {
    private TableController tableController;
    private TableAdapter tableAdapter;

    @Override
    protected FragmentTableNullBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentTableNullBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tableController = new TableController(getActivity());
        tableAdapter = new TableAdapter(new TableAdapter.CallBack() {
            @Override
            public void onCLick(Table table) {
                Intent intent = new Intent(getActivity(), AddBillActivity.class);
                intent.putExtra(AppConstant.TABLE_TABLE_RESTAURANT, table);
                startActivity(intent);
            }
        });
        binding.listTable.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.listTable.setAdapter(tableAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        tableAdapter.setData(tableController.getListTableByStatus(false));
    }
}
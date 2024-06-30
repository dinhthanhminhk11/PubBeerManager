package com.main.pubmanagement.ui.fragment.staff.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.StoreyController;
import com.main.pubmanagement.controller.TableController;
import com.main.pubmanagement.databinding.FragmentAllStoreysAndTableBinding;
import com.main.pubmanagement.model.Storey;
import com.main.pubmanagement.model.Table;
import com.main.pubmanagement.ui.activity.AddBillActivity;
import com.main.pubmanagement.ui.adapter.StoreyAdapter;
import com.main.pubmanagement.ui.adapter.TableAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllStoreysAndTableFragment extends BaseFragment<FragmentAllStoreysAndTableBinding> {
    private StoreyAdapter storeyAdapter;
    private StoreyController storeyController;
    private TableController tableController;
    private TableAdapter tableAdapter;

    @Override
    protected FragmentAllStoreysAndTableBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAllStoreysAndTableBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storeyController = new StoreyController(getActivity());
        tableController = new TableController(getActivity());
        storeyAdapter = new StoreyAdapter(storeyController.getListStorey(), new StoreyAdapter.Callback() {
            @Override
            public void callbackCLick(int position) {
                if (position == 0) {
                    iniDataListAll();
                } else {
                    iniDataListById(position);
                }
            }
        });
        tableAdapter = new TableAdapter(new TableAdapter.CallBack() {
            @Override
            public void onCLick(Table table) {
                if (table.getStatus() == 0) {
                    Intent intent = new Intent(getActivity(), AddBillActivity.class);
                    intent.putExtra(AppConstant.TABLE_TABLE_RESTAURANT, table);
                    startActivity(intent);
                }

            }
        });
        binding.listProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.listProduct.setAdapter(tableAdapter);
        binding.listCategoryHomeFragment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.listCategoryHomeFragment.setAdapter(storeyAdapter);
    }


    private void iniDataListAll() {
        tableAdapter.setData(tableController.getListTable());
    }

    private void iniDataListById(int id) {
        tableAdapter.setData(tableController.getListTableByIdStorey(id));
    }
}
package com.main.pubmanagement.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.databinding.FragmentFundNumberBinding;


public class FundNumberFragment extends BaseFragment<FragmentFundNumberBinding> {


    @Override
    protected FragmentFundNumberBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFundNumberBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
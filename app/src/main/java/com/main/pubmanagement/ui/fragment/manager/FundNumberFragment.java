package com.main.pubmanagement.ui.fragment.manager;

import android.view.LayoutInflater;
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
}
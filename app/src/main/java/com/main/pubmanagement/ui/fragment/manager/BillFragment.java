package com.main.pubmanagement.ui.fragment.manager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.databinding.FragmentBillBinding;


public class BillFragment extends BaseFragment<FragmentBillBinding> {

    @Override
    protected FragmentBillBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBillBinding.inflate(inflater, container, false);
    }
}
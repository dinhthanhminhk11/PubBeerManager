package com.main.pubmanagement.ui.fragment.manager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.databinding.FragmentProductBinding;


public class ProductFragment extends BaseFragment<FragmentProductBinding> {


    @Override
    protected FragmentProductBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProductBinding.inflate(inflater , container , false);
    }
}
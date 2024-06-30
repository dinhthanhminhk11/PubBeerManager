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
import com.main.pubmanagement.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {


    @Override
    protected FragmentHomeBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater , container , false);
    }
}
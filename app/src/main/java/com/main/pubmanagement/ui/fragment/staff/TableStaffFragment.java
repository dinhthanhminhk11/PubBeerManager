package com.main.pubmanagement.ui.fragment.staff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.pubmanagement.R;
import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.databinding.FragmentTableStaffBinding;
import com.main.pubmanagement.ui.adapter.ViewPageAdapter;


public class TableStaffFragment extends BaseFragment<FragmentTableStaffBinding> {
    private ViewPageAdapter viewPager;
    @Override
    protected FragmentTableStaffBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentTableStaffBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = new ViewPageAdapter(this.getChildFragmentManager());
        binding.viewpager.setAdapter(viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
    }
}
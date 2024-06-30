package com.main.pubmanagement.ui.fragment.staff;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.databinding.FragmentReportLatsDayBinding;


public class ReportLatsDayFragment extends BaseFragment<FragmentReportLatsDayBinding> {

    @Override
    protected FragmentReportLatsDayBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentReportLatsDayBinding.inflate(inflater, container, false);
    }
}
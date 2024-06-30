package com.main.pubmanagement.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.main.pubmanagement.ui.fragment.staff.home.AllStoreysAndTableFragment;
import com.main.pubmanagement.ui.fragment.staff.home.TableNullFragment;
import com.main.pubmanagement.ui.fragment.staff.home.UsingTableFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new UsingTableFragment();
            case 2:
                return new TableNullFragment();
            default:
                return new AllStoreysAndTableFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Tất cả";
                break;
            case 1:
                title = "Sử dụng";
                break;
            case 2:
                title = "Còn trống";
                break;
        }
        return title;
    }
}


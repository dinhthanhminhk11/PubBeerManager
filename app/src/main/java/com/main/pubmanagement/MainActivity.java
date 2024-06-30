package com.main.pubmanagement;

import static androidx.core.text.SpannableStringBuilderKt.color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.databinding.ActivityLoginBinding;
import com.main.pubmanagement.databinding.ActivityMainBinding;
import com.main.pubmanagement.sharedpreferences.MySharedPreferences;
import com.main.pubmanagement.ui.activity.LoginActivity;
import com.main.pubmanagement.ui.customview.customNavBar.SlidingRootNav;
import com.main.pubmanagement.ui.customview.customNavBar.SlidingRootNavBuilder;
import com.main.pubmanagement.ui.fragment.SettingFragment;
import com.main.pubmanagement.ui.fragment.manager.BillFragment;
import com.main.pubmanagement.ui.fragment.manager.FundNumberFragment;
import com.main.pubmanagement.ui.fragment.manager.HomeFragment;
import com.main.pubmanagement.ui.fragment.manager.ProductFragment;
import com.main.pubmanagement.ui.fragment.staff.HistoryStaffFragment;
import com.main.pubmanagement.ui.fragment.staff.ReportLatsDayFragment;
import com.main.pubmanagement.ui.fragment.staff.TableStaffFragment;
import com.main.pubmanagement.ui.view.dialog.DialogConfirmCustom;
import com.main.pubmanagement.ui.view.menu.DrawerAdapter;
import com.main.pubmanagement.ui.view.menu.DrawerItem;
import com.main.pubmanagement.ui.view.menu.SimpleItem;
import com.main.pubmanagement.ui.view.menu.SpaceItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_HOME_MANAGER = 0;
    private static final int POS_PRODUCT_MANAGER = 1;
    private static final int POS_BILL_MANAGER = 3;
    private static final int POS_FUND_NUMBER_MANAGER = 4;
    private static final int POS_SETTING_MANAGER = 6;
    private static final int POS_LOGOUT_MANAGER = 7;

    private static final int POS_TABLE_STAFF = 0;
    private static final int POS_HISTORY_STAFF = 2;
    private static final int POS_REPORT_LATS_DAY_STAFF = 3;
    private static final int POS_SETTING_STAFF = 5;
    private static final int POS_LOGOUT_STAFF = 6;
    private static final int ROLE_ADMIN = 0;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private DrawerAdapter adapter;
    private SlidingRootNav slidingRootNav;
    private ActivityMainBinding binding;
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        slidingRootNav = new SlidingRootNavBuilder(this).withMenuOpened(false).withToolbarMenuToggle(binding.toolbar).withContentClickableWhenMenuOpened(false).withSavedState(savedInstanceState).withMenuLayout(R.layout.menu_left_drawer).inject();
        role = MySharedPreferences.getInstance(MainActivity.this).getInt(AppConstant.COLUMN_USER_ROLE, 0);
        binding.titleSelect.setText(role == ROLE_ADMIN ? R.string.home_manager : R.string.table_staff);
        screenIcons = loadScreenIcons(role == ROLE_ADMIN ? R.array.ld_mannager_icon : R.array.ld_staff_icon);
        screenTitles = loadScreenTitles(role == ROLE_ADMIN ? R.array.ld_mannager_title : R.array.ld_staff_title);

        if (role == ROLE_ADMIN) {
            adapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_HOME_MANAGER).setChecked(true),
                    createItemFor(POS_PRODUCT_MANAGER),
                    new SpaceItem(48),
                    createItemFor(POS_BILL_MANAGER),
                    createItemFor(POS_FUND_NUMBER_MANAGER),
                    new SpaceItem(48),
                    createItemFor(POS_SETTING_MANAGER),
                    createItemFor(POS_LOGOUT_MANAGER)
            ));
        } else {
            adapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_TABLE_STAFF).setChecked(true),
                    new SpaceItem(48),
                    createItemFor(POS_HISTORY_STAFF),
                    createItemFor(POS_REPORT_LATS_DAY_STAFF),
                    new SpaceItem(48),
                    createItemFor(POS_SETTING_STAFF),
                    createItemFor(POS_LOGOUT_STAFF)
            ));
        }
        adapter.setListener(this);
        RecyclerView list = findViewById(R.id.listMenuLeftDrawer);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(0);
    }

    @Override
    public void onItemSelected(int position) {
        if (role == ROLE_ADMIN) {
            switch (position) {
                case POS_HOME_MANAGER: {
                    binding.titleSelect.setText(R.string.home_manager);
                    showFragment(new HomeFragment());
                    break;
                }
                case POS_PRODUCT_MANAGER: {
                    binding.titleSelect.setText(R.string.home_manager);
                    showFragment(new ProductFragment());
                    break;
                }
                case POS_BILL_MANAGER: {
                    binding.titleSelect.setText(R.string.home_manager);
                    showFragment(new BillFragment());
                    break;
                }
                case POS_FUND_NUMBER_MANAGER: {
                    binding.titleSelect.setText(R.string.home_manager);
                    showFragment(new FundNumberFragment());
                    break;
                }
                case POS_SETTING_MANAGER: {
                    binding.titleSelect.setText(R.string.table_staff);
                    showFragment(new SettingFragment());
                    break;
                }
                case POS_LOGOUT_MANAGER: {
                    showDialogLogOut();
                    break;
                }
            }
        } else {
            switch (position) {
                case POS_TABLE_STAFF: {
                    binding.titleSelect.setText(R.string.table_staff);
                    showFragment(new TableStaffFragment());
                    break;
                }
                case POS_HISTORY_STAFF: {
                    binding.titleSelect.setText(R.string.table_staff);
                    showFragment(new HistoryStaffFragment());
                    break;
                }
                case POS_REPORT_LATS_DAY_STAFF: {
                    binding.titleSelect.setText(R.string.table_staff);
                    showFragment(new ReportLatsDayFragment());
                    break;
                }
                case POS_SETTING_STAFF: {
                    binding.titleSelect.setText(R.string.table_staff);
                    showFragment(new SettingFragment());
                    break;
                }
                case POS_LOGOUT_STAFF: {
                    showDialogLogOut();
                    break;
                }
            }
        }

        slidingRootNav.closeMenu();
    }

    private String[] loadScreenTitles(int resource) {
        return getResources().getStringArray(resource);
    }

    private Drawable[] loadScreenIcons(int resource) {
        TypedArray ta = getResources().obtainTypedArray(resource);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position]).withIconTint(color(R.color.white)).withTextTint(color(R.color.white)).withBackground(Color.TRANSPARENT).withSelectedIconTint(color(R.color.blue)).withSelectedTextTint(color(R.color.blue)).withSelectedBackGroundTint(R.drawable.background_select_white_activity);
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, fragment).commit();
    }
    private void showDialogLogOut(){
        DialogConfirmCustom dialog = DialogConfirmCustom.create(
                this,
                "Bạn có muốn đăng xuất không",
                "Đồng ý", "Hủy",
                () -> {
                    MySharedPreferences.getInstance(MainActivity.this).putString(AppConstant.COLUMN_USER_USERNAME, "");
                    MySharedPreferences.getInstance(MainActivity.this).putString(AppConstant.COLUMN_USER_PASSWORD, "");
                    MySharedPreferences.getInstance(MainActivity.this).putInt(AppConstant.COLUMN_USER_ROLE, 0);
                    Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
        );
        dialog.show();
    }
}
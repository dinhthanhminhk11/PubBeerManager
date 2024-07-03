package com.main.pubmanagement.ui.fragment.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.main.pubmanagement.base.BaseFragment;
import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.controller.MenuController;
import com.main.pubmanagement.databinding.FragmentProductBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.ui.activity.AddNewMenuActivity;
import com.main.pubmanagement.ui.activity.EditMenuActivity;
import com.main.pubmanagement.ui.activity.ManagerCategoryActivity;
import com.main.pubmanagement.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends BaseFragment<FragmentProductBinding> {
    private MenuController menuController;
    private ProductAdapter productAdapter;
    private List<Menu> menuList;
    private ActivityResultLauncher<Intent> resultLauncher;
    private ActivityResultLauncher<Intent> anotherResultLauncher;
    private MenuType resultData;

    @Override
    protected FragmentProductBinding inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProductBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuController = new MenuController(getActivity());
        productAdapter = new ProductAdapter();
        menuList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(productAdapter);
        iniDataListAll();
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            MenuType resultData = (MenuType) data.getSerializableExtra("Product_fragment");
                            ProductFragment.this.resultData = resultData;
                            binding.name.setText(resultData.getName());
                            if (resultData.getId() == 0) {
                                iniDataListAll();
                            } else {
                                iniDataListById(resultData.getId());
                            }
                        }
                    }
                });

        anotherResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String resultData = data.getStringExtra("Another_Result_Key");
                            if (resultData.equals("add_success")) {///
                                if (ProductFragment.this.resultData != null) {
                                    if (ProductFragment.this.resultData.getId() == 0) {
                                        reloadAll();
                                    } else {
                                        reload(ProductFragment.this.resultData.getId());
                                    }
                                } else {
                                    reloadAll();
                                }
                            }
                        }
                    }
                });


        binding.itemSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerCategoryActivity.class);
                if (resultData != null) {
                    intent.putExtra(AppConstant.TABLE_MENU_TYPE, resultData);
                }
                resultLauncher.launch(intent);
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anotherResultLauncher.launch(new Intent(getActivity(), AddNewMenuActivity.class));
            }
        });
        productAdapter.setCallback(new ProductAdapter.Callback() {
            @Override
            public void click(Menu menu) {
                Intent intent = new Intent(getActivity(), EditMenuActivity.class);
                intent.putExtra(AppConstant.TABLE_MENU, menu);
                anotherResultLauncher.launch(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        iniDataListAll();
    }

    private void iniDataListAll() {
        menuList = menuController.getListMenu();
        productAdapter.setData(menuList);
        binding.count.setText(menuList.size() + " món Sl tồn: " + getCountProduct(menuList));
    }

    public void reloadAll() {
        menuList = menuController.getListMenu();
        productAdapter.setData(menuList);
        binding.count.setText(menuList.size() + " món Sl tồn: " + getCountProduct(menuList));
    }

    private void iniDataListById(int id) {
        List<Menu> menuListSoft = new ArrayList<>();
        for (Menu menu : menuList
        ) {
            if (menu.getIdMenuType() == id) {
                menuListSoft.add(menu);
            }
        }
        productAdapter.setData(menuListSoft);
        binding.count.setText(menuListSoft.size() + " món Sl tồn: " + getCountProduct(menuListSoft));
    }

    private void reload(int id) {
        menuList = menuController.getListMenuByIdMenuType(id);
        productAdapter.setData(menuList);
        binding.count.setText(menuList.size() + " món");
    }

    private int getCountProduct(List<Menu> list) {
        int count =0;
        for (Menu menu : list) {
            count+= menu.getCount();
        }
        return count;
    }
}
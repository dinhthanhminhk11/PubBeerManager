package com.main.pubmanagement.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ItemMenuBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.OrderDetails;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private List<Menu> data;
    private Callback callback;
    private Set<Menu> selectedItems;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private HashMap<Menu, Integer> cartMap;

    public MenuAdapter() {
        this.cartMap = new HashMap<>();
        this.selectedItems = new HashSet<>();
    }

    public void setDataOrderDetail(List<OrderDetails> list) {
        for (OrderDetails o : list
        ) {
            for (Menu menu : data
            ) {
                if (o.getName().equals(menu.getName())) {
                    selectedItems.add(menu);
                    cartMap.put(menu, o.getQuantity());
                }
            }
        }
    }

    public void clear() {
        selectedItems.clear();
        cartMap.clear();
        notifyDataSetChanged();
        if (callback != null) {
            callback.callSum(cartMap);
        }
    }

    public void setData(List<Menu> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Menu item = data.get(position);
        if (item != null) {
            holder.itemMenuBinding.nameUser.setText(item.getName());
            if (item.getDiscount() != 0) {
                holder.itemMenuBinding.content.setVisibility(View.VISIBLE);
                holder.itemMenuBinding.content.setText("Giảm giá: " + item.getDiscount() + "%" + (item.getCount() ==0 ? " Hết hàng" :" Sl tồn: " + item.getCount()));
            } else {
                holder.itemMenuBinding.content.setText(item.getCount() ==0 ? " Hết hàng" :" Sl tồn: " + item.getCount());
                holder.itemMenuBinding.content.setVisibility(View.VISIBLE);
            }
            holder.itemMenuBinding.price.setText(decimalFormat.format(item.getPrice()) + "₫");
            holder.itemMenuBinding.unit.setText(" /" + checkUnit(item.getUnit()));
            holder.itemMenuBinding.getRoot().setBackgroundColor(selectedItems.contains(item) ? holder.itemMenuBinding.unit.getContext().getColor(R.color.color_0D03A9F4) : Color.WHITE);
            holder.itemMenuBinding.count.setVisibility(selectedItems.contains(item) ? View.VISIBLE : View.GONE);
            holder.itemMenuBinding.up.setVisibility(selectedItems.contains(item) ? View.VISIBLE : View.GONE);
            holder.itemMenuBinding.down.setVisibility(selectedItems.contains(item) ? View.VISIBLE : View.GONE);

            int count = cartMap.getOrDefault(item, 0);
            holder.itemMenuBinding.count.setText(String.valueOf(count));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getCount() == 0) {
                        Toast.makeText(v.getContext(), "Món ăn đã hết hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        if (selectedItems.contains(item)) {
//                        if (count > 1) {
//                            cartMap.put(item, count - 1);
//                        } else {
//                            selectedItems.remove(item);
//                            cartMap.remove(item);
//                        }
                        } else {
                            selectedItems.add(item);
                            cartMap.put(item, 1);
                        }
                        notifyItemChanged(position);
                        if (callback != null) {
                            callback.onClick(item.getId());
                            callback.callSum(cartMap);
                        }
                    }
                }
            });

            holder.itemMenuBinding.down.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    if (cartMap.containsKey(item)) {
                        int count = cartMap.get(item);
                        if (count > 1) {
                            cartMap.put(item, count - 1);
                        } else {
                            cartMap.remove(item);
                            selectedItems.remove(item);
                        }
                        notifyDataSetChanged();
                        if (callback != null) {
                            callback.callSum(cartMap);
                        }
                    }
                }
            });

            holder.itemMenuBinding.up.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int currentCount = cartMap.containsKey(item) ? cartMap.get(item) : 0;
                    if (currentCount >= item.getCount()) {
                        Toast.makeText(view.getContext(), "Không thể thêm quá số lượng hiện có", Toast.LENGTH_SHORT).show();
                    } else {
                        if (selectedItems.contains(item)) {
                            cartMap.put(item, currentCount + 1);
                        } else {
                            selectedItems.add(item);
                            cartMap.put(item, 1);
                        }
                        notifyDataSetChanged();
                        if (callback != null) {
                            callback.callSum(cartMap);
                        }
                    }
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMenuBinding itemMenuBinding;

        public ViewHolder(@NonNull ItemMenuBinding binding) {
            super(binding.getRoot());
            itemMenuBinding = binding;
        }
    }

    public interface Callback {
        void onClick(int id);

        void callSum(HashMap<Menu, Integer> cartMap);
    }

    private String checkUnit(int id) {
        switch (id) {
            case 0:
                return "Tháp";
            case 1:
                return "Đĩa";
            case 2:
                return "Con";
            case 3:
                return "Nồi";
            case 4:
                return "Kg";
            case 5:
                return "Suất";
            case 6:
                return "Bát tô";
            case 7:
                return "Ly";
            case 8:
                return "Chai";
            case 9:
                return "Lon";
        }
        return "";
    }
}

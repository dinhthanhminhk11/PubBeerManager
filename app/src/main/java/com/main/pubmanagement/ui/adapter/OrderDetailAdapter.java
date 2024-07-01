package com.main.pubmanagement.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.databinding.ItemMenuBinding;
import com.main.pubmanagement.model.Menu;
import com.main.pubmanagement.model.OrderDetails;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private List<OrderDetails> data;
    private Callback callback;
    private HashMap<OrderDetails, Integer> cartMap;

    public OrderDetailAdapter() {
        this.cartMap = new HashMap<>();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setData(List<OrderDetails> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface Callback {
        void onClick(int id);

        void callSum(HashMap<OrderDetails, Integer> cartMap);
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        OrderDetails item = data.get(position);
        if (item != null) {
            holder.itemMenuBinding.nameUser.setText(item.getName());
            if (item.getDiscount() != 0) {
                holder.itemMenuBinding.content.setVisibility(View.VISIBLE);
                holder.itemMenuBinding.content.setText("Giảm giá: " + item.getDiscount() + "%");
            } else {
                holder.itemMenuBinding.content.setVisibility(View.GONE);
            }
            holder.itemMenuBinding.price.setText(decimalFormat.format(item.getPrice()) + "₫");
            holder.itemMenuBinding.count.setVisibility(View.VISIBLE);
            holder.itemMenuBinding.up.setVisibility(View.GONE);
            holder.itemMenuBinding.down.setVisibility(View.GONE);

            holder.itemMenuBinding.count.setText("Số lượng " + item.getQuantity());

            holder.itemMenuBinding.up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentCount = item.getQuantity();
                    item.setQuantity(currentCount + 1);
                    holder.itemMenuBinding.count.setText(String.valueOf(item.getQuantity()));
                    cartMap.put(item, item.getQuantity());
                    callback.callSum(cartMap);
                    notifyDataSetChanged();
                }
            });

            holder.itemMenuBinding.down.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int currentCount = item.getQuantity();
                    if (currentCount > 1) {
                        item.setQuantity(currentCount - 1);
                        holder.itemMenuBinding.count.setText(String.valueOf(item.getQuantity()));
                        cartMap.put(item, item.getQuantity());
                    } else {
                        data.remove(position);
                        cartMap.remove(item);
                    }
                    callback.callSum(cartMap);
                    notifyDataSetChanged();
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
}

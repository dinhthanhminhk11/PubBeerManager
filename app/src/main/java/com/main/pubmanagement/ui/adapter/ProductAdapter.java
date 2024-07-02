package com.main.pubmanagement.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.databinding.ItemProductBinding;
import com.main.pubmanagement.model.Menu;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private List<Menu> data;

    public ProductAdapter() {
    }

    public void setData(List<Menu> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Menu item = data.get(position);
        if (item != null) {
            holder.binding.name.setText(item.getName());
            if (item.getDiscount() != 0) {
                holder.binding.content.setVisibility(View.VISIBLE);
                holder.binding.content.setText("Giảm giá: " + item.getDiscount() + "%");
            } else {
                holder.binding.content.setVisibility(View.GONE);
            }
            holder.binding.price.setText(decimalFormat.format(item.getPrice()) + "₫");
            holder.binding.unit.setText(" /" + checkUnit(item.getUnit()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
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

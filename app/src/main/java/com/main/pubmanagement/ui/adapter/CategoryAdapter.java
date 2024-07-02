package com.main.pubmanagement.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ItemCatogoryBinding;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.model.Storey;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;
    private List<Storey> data;
    private Callback callback;

    public CategoryAdapter(Callback callback) {
        this.callback = callback;
    }

    public void setData(List<Storey> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void selectItem(int position) {
        if (position >= 0 && position < getItemCount()) {
            row_index = position;
            selected = false;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryAdapter.ViewHolder(ItemCatogoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Storey storey = data.get(position);
        if (storey != null) {
            holder.binding.name.setText(storey.getName());
            if (selected) {
                if (position == 0) {
                    callback.callbackCLick(new MenuType(storey.getId(), storey.getName()));
                    holder.itemView.setBackgroundColor(holder.itemView.getContext().getColor(R.color.color_0D03A9F4));
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.itemView.setBackgroundColor(holder.itemView.getContext().getColor(R.color.color_0D03A9F4));
                } else {
                    holder.itemView.setBackgroundColor(holder.itemView.getContext().getColor(R.color.white));
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    callback.callbackCLick(new MenuType(storey.getId(), storey.getName()));
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
        private ItemCatogoryBinding binding;

        public ViewHolder(@NonNull ItemCatogoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface Callback {
        void callbackCLick(MenuType menuType);
    }
}

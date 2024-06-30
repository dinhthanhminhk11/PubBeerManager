package com.main.pubmanagement.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ItemStoreyBinding;
import com.main.pubmanagement.model.Storey;

import java.util.List;

public class StoreyAdapter extends RecyclerView.Adapter<StoreyAdapter.ViewHolder> {
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;
    private List<Storey> data;
    private Callback callback;

    public StoreyAdapter(List<Storey> data, Callback callback) {
        this.data = data;
        this.callback = callback;
    }

    @NonNull
    @Override
    public StoreyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemStoreyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Storey storey = data.get(position);
        if (storey != null) {
            holder.itemStoreyBinding.name.setText(storey.getName());
            if (selected) {
                if (position == 0) {
                    callback.callbackCLick(0);
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select);
                    holder.itemStoreyBinding.name.setTextColor(Color.WHITE);
                    holder.itemStoreyBinding.name.setTypeface(null, Typeface.BOLD);
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select);
                    holder.itemStoreyBinding.name.setTextColor(Color.WHITE);
                    holder.itemStoreyBinding.name.setTypeface(null, Typeface.BOLD);
                } else {
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_no_select);
                    holder.itemStoreyBinding.name.setTextColor(Color.GRAY);
                    holder.itemStoreyBinding.name.setTypeface(null, Typeface.NORMAL);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    callback.callbackCLick(storey.getId());
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
        private ItemStoreyBinding itemStoreyBinding;

        public ViewHolder(ItemStoreyBinding binding) {
            super(binding.getRoot());
            this.itemStoreyBinding = binding;
        }
    }

    public interface Callback {
        void callbackCLick(int position);
    }
}

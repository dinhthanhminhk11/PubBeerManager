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
import com.main.pubmanagement.databinding.ItemPaymentFileterBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdapterListUserFilter extends RecyclerView.Adapter<AdapterListUserFilter.ViewHolder> {
    private Set<Integer> selectedIndices = new HashSet<>();
    private List<User> data;
    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public AdapterListUserFilter(List<User> data) {
        this.data = data;
    }

    public void clearSelections() {
        selectedIndices.clear();
        notifyDataSetChanged();
        callback.callbackPositions(getSelectedUsers());
    }

    @NonNull
    @Override
    public AdapterListUserFilter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPaymentFileterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListUserFilter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User User = data.get(position);
        if (User != null) {
            holder.itemCategoryHomefragmentBinding.name.setText(User.getName());
            if (selectedIndices.contains(position)) {
                holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select_green);
                holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.WHITE);
                holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.BOLD);
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                holder.itemView.setBackgroundResource(R.drawable.back_ground_item_no_select_green);
                holder.itemCategoryHomefragmentBinding.name.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color4CAF50));
                holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.NORMAL);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedIndices.contains(position)) {
                        selectedIndices.remove(position);
                    } else {
                        selectedIndices.add(position);
                    }
                    notifyDataSetChanged();
                    callback.callbackPositions(getSelectedUsers());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private List<User> getSelectedUsers() {
        List<User> selectedUsers = new ArrayList<>();
        for (Integer index : selectedIndices) {
            selectedUsers.add(data.get(index));
        }
        return selectedUsers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPaymentFileterBinding itemCategoryHomefragmentBinding;

        public ViewHolder(ItemPaymentFileterBinding binding) {
            super(binding.getRoot());
            this.itemCategoryHomefragmentBinding = binding;
        }
    }

    public interface Callback {
        void callbackPositions(List<User> selectedUsers);
    }
}

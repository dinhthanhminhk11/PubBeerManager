package com.main.pubmanagement.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.constant.AppConstant;
import com.main.pubmanagement.databinding.ItemUserBinding;
import com.main.pubmanagement.model.User;
import com.main.pubmanagement.ui.activity.EditMemberActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> data;

    public void setData(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User item = data.get(position);
        if (item != null) {
            holder.binding.name.setText(item.getName());
            holder.binding.username.setText(item.getUsername());
            holder.binding.phone.setText(item.getPhone());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), EditMemberActivity.class);
                    intent.putExtra(AppConstant.TABLE_USER , item);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUserBinding binding;

        public ViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

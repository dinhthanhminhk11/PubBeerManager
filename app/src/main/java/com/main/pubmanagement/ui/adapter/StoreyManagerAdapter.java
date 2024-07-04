package com.main.pubmanagement.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ItemCatogoryBinding;
import com.main.pubmanagement.databinding.ItemStoreyBinding;
import com.main.pubmanagement.databinding.ItemStoreyManagerBinding;
import com.main.pubmanagement.model.MenuType;
import com.main.pubmanagement.model.Storey;

import java.util.List;

public class StoreyManagerAdapter extends RecyclerView.Adapter<StoreyManagerAdapter.ViewHolder> {

    private List<Storey> data;
    private Callback callback;

    public void setData(List<Storey> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public StoreyManagerAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public StoreyManagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemStoreyManagerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull StoreyManagerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Storey storey = data.get(position);
        if (storey != null) {
            holder.itemStoreyBinding.name.setText(storey.getName());
            holder.itemStoreyBinding.countTable.setText(storey.getCountTable() + " bàn");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.callbackCLick(storey);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemStoreyManagerBinding itemStoreyBinding;

        public ViewHolder(ItemStoreyManagerBinding binding) {
            super(binding.getRoot());
            this.itemStoreyBinding = binding;
        }
    }

    public interface Callback {
        void callbackCLick(Storey so);
    }
}

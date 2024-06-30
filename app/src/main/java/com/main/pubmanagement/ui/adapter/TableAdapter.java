package com.main.pubmanagement.ui.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.R;
import com.main.pubmanagement.databinding.ItemTableBinding;
import com.main.pubmanagement.model.Table;

import java.util.List;
import java.util.function.Consumer;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Table> data;

    private CallBack callBack;

    public void setData(List<Table> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public TableAdapter(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void onCLick(Table table);
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTableBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        Table table = data.get(position);
        if (table != null) {
            holder.itemTableBinding.name.setText(table.getName());
            if (table.getNameStorey().equals("")) {
                holder.itemTableBinding.nameStorey.setVisibility(View.GONE);
            } else {
                holder.itemTableBinding.nameStorey.setVisibility(View.VISIBLE);
                holder.itemTableBinding.nameStorey.setText("(" + table.getNameStorey() + ")");
            }
            holder.itemTableBinding.chair.setText(table.getCountChair() + " người");

            if (table.getStatus() == 1) {
                holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select);
                holder.itemTableBinding.name.setTextColor(Color.WHITE);
                holder.itemTableBinding.nameStorey.setTextColor(Color.WHITE);
                holder.itemTableBinding.chair.setTextColor(Color.WHITE);
                holder.itemTableBinding.name.setTypeface(null, Typeface.BOLD);
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                holder.itemView.setBackgroundResource(R.drawable.back_ground_item_no_select);
                holder.itemTableBinding.name.setTextColor(Color.BLACK);
                holder.itemTableBinding.nameStorey.setTextColor(Color.GRAY);
                holder.itemTableBinding.chair.setTextColor(Color.BLACK);
                holder.itemTableBinding.name.setTypeface(null, Typeface.NORMAL);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onCLick(table);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTableBinding itemTableBinding;

        public ViewHolder(@NonNull ItemTableBinding biding) {
            super(biding.getRoot());
            itemTableBinding = biding;
        }
    }
}

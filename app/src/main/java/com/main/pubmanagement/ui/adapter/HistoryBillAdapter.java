package com.main.pubmanagement.ui.adapter;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.main.pubmanagement.databinding.ItemHistoryBillBinding;
import com.main.pubmanagement.model.Bill;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryBillAdapter extends RecyclerView.Adapter<HistoryBillAdapter.ViewHolder> {

    private List<Bill> data;

    public void setData(List<Bill> data) {
        this.data = data;
    }

    private NumberFormat fm = new DecimalFormat("#,###");

    @NonNull
    @Override
    public HistoryBillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemHistoryBillBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HistoryBillAdapter.ViewHolder holder, int position) {
        Bill bill = data.get(position);
        if (bill != null) {
            LocalDateTime currentDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(bill.getTime())), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            holder.binding.title.setText(bill.getNameTable());
            holder.binding.content.setText(bill.getCountPerson() + " khách " + bill.getType() + " ");
            holder.binding.dateAndTime.setText(formattedDateTime);
            holder.binding.price.setText("+" + fm.format(Integer.parseInt(String.valueOf(bill.getPrice()))));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBillBinding binding;

        public ViewHolder(@NonNull ItemHistoryBillBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

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
import com.main.pubmanagement.databinding.ItemPaymentBinding;
import com.main.pubmanagement.model.Payment;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;
    private List<Payment> data;

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public PaymentAdapter(List<Payment> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPaymentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Payment payment = data.get(position);
        if (payment != null) {
            holder.itemCategoryHomefragmentBinding.name.setText(payment.getName());

            if (selected) {
                if (position == 0) {
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select_green);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.BOLD);
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select_green);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.BOLD);
                } else {
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_no_select_green);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color4CAF50));
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.NORMAL);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();
                    callback.callbackPosition(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPaymentBinding itemCategoryHomefragmentBinding;

        public ViewHolder(ItemPaymentBinding binding) {
            super(binding.getRoot());
            this.itemCategoryHomefragmentBinding = binding;
        }
    }

    public interface Callback {
        void callbackPosition(int id);
    }
}

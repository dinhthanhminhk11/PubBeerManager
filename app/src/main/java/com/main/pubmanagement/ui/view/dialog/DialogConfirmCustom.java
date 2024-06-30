package com.main.pubmanagement.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.main.pubmanagement.databinding.LayoutDialogConfirmBinding;

public class DialogConfirmCustom extends Dialog {

    private final String content;
    private final String textConfirm;
    private final String textCancel;
    private LayoutDialogConfirmBinding binding;
    private CallBack callBack;

    public DialogConfirmCustom(Context context, String content, String textConfirm, String textCancel, CallBack callBack) {
        super(context);
        this.content = content;
        this.callBack = callBack;
        this.textConfirm = textConfirm;
        this.textCancel = textCancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = LayoutDialogConfirmBinding.inflate(inflater);
        setContentView(binding.getRoot());

        binding.content.setText(content);
        binding.cancelButton.setText(textCancel);
        binding.confirmButton.setText(textConfirm);

        binding.close.setOnClickListener(v -> dismiss());

        binding.cancelButton.setOnClickListener(v -> dismiss());

        binding.confirmButton.setOnClickListener(v -> {
            callBack.onClickSuccess();
            dismiss();
        });
    }

    public static DialogConfirmCustom create(Context context, String content, String textConfirm, String textCancel, CallBack onLogoutClick) {
        return new DialogConfirmCustom(context, content, textConfirm, textCancel, onLogoutClick);
    }

    public interface CallBack {
        void onClickSuccess();
    }
}

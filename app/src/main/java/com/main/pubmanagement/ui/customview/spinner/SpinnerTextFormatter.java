package com.main.pubmanagement.ui.customview.spinner;

import android.text.Spannable;

public interface SpinnerTextFormatter<T> {

    Spannable format(T item);
}

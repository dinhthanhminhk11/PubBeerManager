package com.main.pubmanagement.ui.customview.customNavBar.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.main.pubmanagement.ui.customview.customNavBar.SlidingRootNavLayout;

public class HiddenMenuClickConsumer extends View {

    private SlidingRootNavLayout menuHost;

    public HiddenMenuClickConsumer(Context context) {
        super(context);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        return menuHost.isMenuClosed();
    }

    public void setMenuHost(SlidingRootNavLayout layout) {
        this.menuHost = layout;
    }
}


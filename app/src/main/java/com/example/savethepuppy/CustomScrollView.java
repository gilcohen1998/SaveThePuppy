package com.example.savethepuppy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

// A custom scroll view class that allow using scroll view inside an other scroll view

public class CustomScrollView extends ScrollView {

    private ScrollView childScrollView;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChildScrollView(ScrollView childScrollView) {
        this.childScrollView = childScrollView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (childScrollView != null) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                // Allow the inner ScrollView to start scrolling
                childScrollView.requestDisallowInterceptTouchEvent(false);
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                // Prevent the outer ScrollView from scrolling when the inner ScrollView is scrolling
                childScrollView.requestDisallowInterceptTouchEvent(true);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}

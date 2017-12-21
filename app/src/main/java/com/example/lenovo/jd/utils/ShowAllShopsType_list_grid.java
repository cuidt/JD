package com.example.lenovo.jd.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class ShowAllShopsType_list_grid extends GridView {

    public ShowAllShopsType_list_grid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
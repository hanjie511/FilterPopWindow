package com.example.hj.myfilterpopwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class HJGridView extends GridView {
    public HJGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public HJGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public HJGridView(Context context) {
        super(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

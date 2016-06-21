package com.lxl.quicklypicdemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/6/20.
 */
public class SquareLayout extends RelativeLayout {
	public SquareLayout(Context context) {
		super(context);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SquareLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	//定制一个正方形
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}

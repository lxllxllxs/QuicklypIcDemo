package com.lxl.quicklypicdemo.Adapter;

import android.view.View;

import com.lxl.quicklypicdemo.bean.Imag;

/**
 * Created by Administrator on 2016/6/20.
 */
public interface RecyclerOnclickListener {
	void onItemClick(View view , Imag data,int postion);
}

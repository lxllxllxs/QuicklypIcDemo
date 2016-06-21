package com.lxl.quicklypicdemo.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lxl.quicklypicdemo.Adapter.folderAdapter;
import com.lxl.quicklypicdemo.R;
import com.lxl.quicklypicdemo.Utils.ScreenUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class folderWindow extends PopupWindow {
	private View mWindow;
	private RecyclerView mRecyclerView;
	private ListView listView;
	public static boolean isShow=false;
	private  Context context;
	private List<String> mdata;

	public folderWindow(Context context){
		this.context=context;
		mWindow= LayoutInflater.from(context).inflate(R.layout.folderwindow,null);
		this.setContentView(mWindow);
		this.setWidth(ScreenUtils.getScreenWidth(context));
		this.setHeight(ScreenUtils.getScreenHeight(context)- ScreenUtils.dip2px(context, 96)-100);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setTouchable(true);
		this.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		init();
	}

	public void init(){
		mRecyclerView=(RecyclerView) mWindow.findViewById(R.id.fwrv);
		folderAdapter adapter=new folderAdapter(context,mdata);
		listView.setAdapter(adapter);
		Log.d("INIT", "init: ");

	}

	@Override
	public void showAsDropDown(View anchor) {
		super.showAsDropDown(anchor);
		Log.d("showAsDrop'", "showAsDropDown: ");
	}

	@Override
	public void dismiss() {
		super.dismiss();
		Log.d("Diss", "dismiss: ");
	}


}

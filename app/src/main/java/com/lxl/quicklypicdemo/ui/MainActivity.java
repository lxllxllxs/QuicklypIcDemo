package com.lxl.quicklypicdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.lxl.quicklypicdemo.Adapter.RecyclerOnclickListener;
import com.lxl.quicklypicdemo.Adapter.RecylceAdapter;
import com.lxl.quicklypicdemo.R;
import com.lxl.quicklypicdemo.Utils.GridSpacingItemDecoration;
import com.lxl.quicklypicdemo.Utils.ScreenUtils;
import com.lxl.quicklypicdemo.bean.Imag;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends Activity {
	private  static  final String TAG="MainActivity";
	private RecyclerView recyclerView;
	private List<Imag> mDatas;
	private List<String> withoudup;
	private View view;
	private folderWindow fw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	public void init(){
		initData();
		fw=new folderWindow(this,withoudup);//包含文件夹信息
		recyclerView=(RecyclerView) findViewById(R.id.rv);
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
		recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtils.dip2px(this, 2), false));
		view=findViewById(R.id.view);

		RecylceAdapter recylceAdapter=new RecylceAdapter(getApplicationContext(),mDatas);

		recylceAdapter.setOnItemClickListener(new RecyclerOnclickListener() {
			@Override
			public void onItemClick(View view, Imag data,int postion) {
				if (data==null){
					Toast.makeText(getApplicationContext(),"NULL",Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(getApplicationContext(),postion+"",Toast.LENGTH_SHORT).show();
					Intent i=new Intent(MainActivity.this,ViewPagerActivity.class);
					Bundle bundle=new Bundle();
					bundle.putSerializable("Images", (Serializable) mDatas);
					bundle.putInt("postion",postion);
					i.putExtras(bundle);
					startActivity(i);
				}
			}
		});
		recyclerView.setAdapter(recylceAdapter);
		(findViewById(R.id.alltv)).setOnClickListener(onClickListener);
	}


	private View.OnClickListener onClickListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){
				case  R.id.alltv:
					if(fw.isShowing()){
						fw.dismiss();
					}else {
						//获得控件的位置
						int[] location=new int[2];
						v.getLocationOnScreen(location);
						//在控件上方显示
						fw.showAtLocation(v, Gravity.NO_GRAVITY,location[0],location[1]-fw.getHeight());
					}
					break;
			}
		}
	};


	protected void initData() {

		Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		Imag imag;
		File file;
		String folderpath;
		List<String> folders=new ArrayList<>();
		mDatas = new ArrayList<>();
		while (cursor.moveToNext()) {

			imag = new Imag();
			imag.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
			//所有文件夹
			mDatas.add(imag);
			file=new File(imag.getPath());
			folderpath=file.getParent();
			folders.add(folderpath);
		}
		Log.d(TAG, "initData: "+folders.size());
		withoudup=new ArrayList<>(new HashSet(folders));
		Log.d(TAG, "initData: "+mDatas.size()+"=="+withoudup.size());


	}

}

package com.lxl.quicklypicdemo.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lxl.quicklypicdemo.Adapter.RecyclerOnclickListener;
import com.lxl.quicklypicdemo.Adapter.RecylceAdapter;
import com.lxl.quicklypicdemo.R;
import com.lxl.quicklypicdemo.Utils.GridSpacingItemDecoration;
import com.lxl.quicklypicdemo.Utils.ScreenUtils;
import com.lxl.quicklypicdemo.bean.Imag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	private  static  final String TAG="MainActivity";
	private RecyclerView recyclerView;
	private List<Imag> mDatas;
	private View view;
	private folderWindow fw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fw=new folderWindow(this);
		init();
	}

	public void init(){
		initData();
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
					/*1.
					*
					* */
					if (!fw.isShowing()) {
						Log.d(TAG, "onClick: false");
						fw.showAsDropDown(v);
					}else{
						Log.d(TAG, "onClick: true");
						fw.dismiss();
					}
					break;


			}
		}
	};

	private void scanMp3() {
		ContentResolver cr=getContentResolver();
		Cursor c;
		Cursor cmp3=cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
		Log.d(TAG, "scan: "+MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);


		while (cmp3.moveToNext()){
			int id =cmp3.getColumnIndex(MediaStore.Audio.Media._ID);
			String path=cmp3.getString(cmp3.getColumnIndex(MediaStore.Audio.Media.DATA));
			//Log.d(TAG, "scan: "+cmp3.getString(id)+path);


		}

	}

	protected void initData() {

		Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		Imag imag;
		mDatas = new ArrayList<>();
		while (cursor.moveToNext()) {

			imag = new Imag();
			imag.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));

			mDatas.add(imag);
		}
		Log.d(TAG, "initData: "+mDatas.size());

	}

}

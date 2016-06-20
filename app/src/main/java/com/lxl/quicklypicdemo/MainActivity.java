package com.lxl.quicklypicdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private  static  final String TAG="MainActivity";
	private  GridAdapter gridAdapter;
	private RecyclerView recyclerView;
	private List<Imag> mDatas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//init();
	}

	public void init(){
		initData();
		recyclerView=(RecyclerView) findViewById(R.id.rv);
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
	//	recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtils.dip2px(this, 2), false));
	//	RecylceAdapter recylceAdapter=new RecylceAdapter(getApplicationContext(),mDatas);


	//	recyclerView.setAdapter(recylceAdapter);
	}


	private View.OnClickListener onClickListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){

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
			Log.d(TAG, "scan: "+cmp3.getString(id)+path);


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
			Log.d(TAG, "initData: "+mDatas.size());
		}


	}

}

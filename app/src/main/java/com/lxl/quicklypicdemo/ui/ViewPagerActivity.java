package com.lxl.quicklypicdemo.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lxl.quicklypicdemo.Adapter.FragementAdapter;
import com.lxl.quicklypicdemo.R;
import com.lxl.quicklypicdemo.bean.Imag;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ViewPagerActivity extends AppCompatActivity {
	private ViewPager viewPager;
	private List<Imag> mdatas;
	private FragementAdapter fragementAdapter;
	private static final String TAG="ViewPagerActivity";
	private String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
		Bundle bundle=getIntent().getExtras();
		mdatas=(List<Imag>)bundle.getSerializable("Images");
		//传进图片路径
		path=bundle.getString("path");
		Log.d(TAG, "onCreate: "+mdatas.size()+"=="+path);

		init();
	}


	public void init(){
		viewPager=(ViewPager)findViewById(R.id.viewpager);
		fragementAdapter=new FragementAdapter(getSupportFragmentManager());
		fragementAdapter.setDatas(mdatas);
		viewPager.setAdapter(fragementAdapter);

	}
}

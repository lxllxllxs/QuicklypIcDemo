package com.lxl.quicklypicdemo.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lxl.quicklypicdemo.Adapter.FragementAdapter;
import com.lxl.quicklypicdemo.R;
import com.lxl.quicklypicdemo.bean.Imag;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ViewPagerActivity extends FragmentActivity {
	private ViewPager viewPager;
	private List<Imag> mdatas;
	private FragementAdapter fragementAdapter;
	private static final String TAG="ViewPagerActivity";
	private String path;
	private  int postion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
		Bundle bundle=getIntent().getExtras();
		mdatas=(List<Imag>)bundle.getSerializable("Images");
		//传进当前位置
		postion=bundle.getInt("postion");
		Log.d(TAG, "onCreate: "+mdatas.size()+"=="+postion);

		init();
	}


	public void init(){
		viewPager=(ViewPager)findViewById(R.id.viewpager);
		fragementAdapter=new FragementAdapter(getSupportFragmentManager());
		fragementAdapter.setDatas(mdatas);
		//设置Adapter
		viewPager.setAdapter(fragementAdapter);
		//设置当前ViewPager的位置
		viewPager.setCurrentItem(postion);

	}
}

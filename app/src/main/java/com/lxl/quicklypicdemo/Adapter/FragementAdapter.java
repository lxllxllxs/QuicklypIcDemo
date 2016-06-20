package com.lxl.quicklypicdemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lxl.quicklypicdemo.bean.Imag;
import com.lxl.quicklypicdemo.ui.MyFragement;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class FragementAdapter extends FragmentPagerAdapter {
	String path;
	private List<Imag> datas;
	public FragementAdapter(FragmentManager fm) {
		super(fm);
		Log.d("FragmentAgapter'", "FragementAdapter: ");
	}


	@Override
	public Fragment getItem(int position) {
		return MyFragement.getFragment(datas.get(position).getPath());

	}

	@Override
	public int getCount() {
		return datas.size();
	}

	public void setDatas(List<Imag> datas){
		this.datas=datas;
	}

}

package com.lxl.quicklypicdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxl.quicklypicdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class folderAdapter extends BaseAdapter {
	private List<String> mdatas;

	private Context context;

	public folderAdapter(Context context,List<String> mdatas){
		this.context=context;
		this.mdatas=mdatas;
	}


	@Override
	public int getCount() {
		return mdatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mdatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null){
			convertView= LayoutInflater.from(context).inflate(R.layout.folderlist,null);
		}
		TextView tv=(TextView)convertView.findViewById(R.id.foldelisttv);
		tv.setText(mdatas.get(position));

		return convertView;
	}
}

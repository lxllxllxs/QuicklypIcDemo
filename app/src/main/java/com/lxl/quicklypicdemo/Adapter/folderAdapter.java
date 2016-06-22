package com.lxl.quicklypicdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxl.quicklypicdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class folderAdapter extends RecyclerView.Adapter {
	private List<String> mdatas;

	private Context context;

	public folderAdapter(Context context, List<String> data){
		this.context=context;
		this.mdatas=data;
		//	this.onclicker=onClicListener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view=LayoutInflater.from(context).inflate(R.layout.folderlist,parent,false);
		Viewholder viewholder=new Viewholder(view);

		return viewholder;
	}
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final Viewholder myholder=(Viewholder)holder;
		myholder.tv.setText(mdatas.get(position));

	}

	@Override
	public int getItemCount() {
		return mdatas.size();
	}

	public static class Viewholder extends RecyclerView.ViewHolder {
		TextView tv;
		public Viewholder(View itemView) {
			super(itemView);
			tv=(TextView)itemView.findViewById(R.id.foldelisttv);
		}

	}



/*	@Override
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
	}*/
}

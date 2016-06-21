package com.lxl.quicklypicdemo.ui;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lxl.quicklypicdemo.R;

import java.io.File;

/**
 * Created by Administrator on 2016/6/20.
 */
public class MyFragement extends Fragment {
	private  String path;
	private  static final String TAG="MyFragment";

	public MyFragement (String path){
		this.path=path;
		Log.d(TAG, "MyFragement: "+path);
	}

	public static  Fragment getFragment(String path){
		return new MyFragement(path);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View convertView= inflater.inflate(R.layout.myfragement,container,false);
		final  ImageView iv=(ImageView) convertView.findViewById(R.id.fgiv);
		//使用框架Glide加载图片，但加载动图需要改动，不能正常显示
		if (!path.endsWith(".gif")) {
			Glide.with(container.getContext())
					.load(new File(path))
					.asBitmap()
					.into(new SimpleTarget<Bitmap>() {
						@Override
						public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
							iv.setImageBitmap(resource);
						}
					});
		}else {
			//加载Gif
			Glide.with(container.getContext()).load(new File(path)).into(new GlideDrawableImageViewTarget(iv));
		}
		Log.d(TAG, "onCreateView: "+1);
		return convertView;
	}

}

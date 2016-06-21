package com.lxl.quicklypicdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.lxl.quicklypicdemo.R;

/**
 * Created by Administrator on 2016/6/21.
 */
public class my extends Activity{
	private  folderWindow fw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my);
		fw=new folderWindow(my.this);
		final Button b=(Button)findViewById(R.id.btn1);
		(findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (fw.isShowing()){
					fw.dismiss();
				}else {
					int[] location=new int[2];
					v.getLocationOnScreen(location);
					fw.showAtLocation(v, Gravity.NO_GRAVITY,location[0],location[1]-fw.getHeight());
				}
			}
		});


	}
}

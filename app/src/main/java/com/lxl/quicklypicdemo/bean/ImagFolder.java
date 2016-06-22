package com.lxl.quicklypicdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImagFolder  implements Serializable{
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private  String path;

	private List<ImagFolder> folders=new ArrayList<>();

	public List<ImagFolder> getFolders(){
		return  folders;
	}


}

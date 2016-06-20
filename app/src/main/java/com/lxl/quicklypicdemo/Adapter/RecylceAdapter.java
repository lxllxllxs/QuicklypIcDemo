package com.lxl.quicklypicdemo.Adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lxl.quicklypicdemo.bean.Imag;
import com.lxl.quicklypicdemo.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class RecylceAdapter extends RecyclerView.Adapter {

	private Context context;
	private File file= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ImageCache");
	private  List<Imag> data;

	private  static final String TAG="RecylceAdapter";
	private RecyclerOnclickListener mOnClickerListener=null;
	private static  int id;
	public RecylceAdapter(Context context, List<Imag> data){
		this.context=context;
		this.data=data;
	//	this.onclicker=onClicListener;
	}

	public  void setOnItemClickListener(RecyclerOnclickListener Recycleonclickener){
		mOnClickerListener=Recycleonclickener;
	}


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view=LayoutInflater.from(context).inflate(R.layout.gridviewitem,parent,false);
		Viewholder viewholder=new Viewholder(view);

		return viewholder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

		final Viewholder myholder=(Viewholder)holder;

		myholder.iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnClickerListener!=null){
					mOnClickerListener.onItemClick(v,data.get(position),position);
				}
			}
		});
//		imageLoader.displayImage("file://"+data.get(position).getPath(), myholder.iv);
		Glide.with(context)
				.load(new File(data.get(position).getPath()))
				.centerCrop()
				.thumbnail(0.5f)
				//.placeholder(R.drawable.image_placeholder)
				.error(R.drawable.erro)
				.dontAnimate()
				.into(myholder.iv);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}


	public static class Viewholder extends RecyclerView.ViewHolder {
		ImageView iv;
		public Viewholder(View itemView) {
			super(itemView);
			iv=(ImageView)itemView.findViewById(R.id.gviv);
		}

	}

	public List<Imag> getImags(){
		return  data;
	}

	private ImageLoader getImagloader(){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(context)
				.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
				.threadPoolSize(3)//线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100) //缓存的文件数量
				.discCache(new UnlimitedDiscCache(file))//自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.build();//开始构建
		ImageLoader.getInstance().init(config);//全局初始化此配置

		return  ImageLoader.getInstance();
	}


}

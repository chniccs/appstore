package com.yufenit.appstore.activity;

import java.io.File;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.fragment.LoadingUI;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.holder.DetailAppInfoHolder;
import com.yufenit.appstore.holder.DetailDesHolder;
import com.yufenit.appstore.holder.DetailDownHolder;
import com.yufenit.appstore.holder.DetailPicsHolder;
import com.yufenit.appstore.holder.DetailSafeHolder;
import com.yufenit.appstore.protocal.DetailProtocal;
import com.yufenit.appstore.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.activity
 * @类名: DetialActivity
 * @创建者: chniccs
 * @创建时间: 2015-8-27 下午8:26:15
 * @描述: 详情页面
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public class DetailActivity extends ActionBarActivity
{

	private ActionBar	mActionBar;
	
	private DetailProtocal mProtocal;
	
	private LoadingUI	mLoadingUI;
	private String mPackageName;

	private AppInfoBean	mBean;

	private BitmapUtils	mBitmapUtils;
	
	@ViewInject(R.id.detail_appinfo)
	private FrameLayout mAppInfoContainer;
	
	@ViewInject(R.id.detail_safe)
	private FrameLayout mSafeContainer;
	
	@ViewInject(R.id.detail_pictures)
	private FrameLayout mPicsContainer;
	
	@ViewInject(R.id.detail_des)
	private FrameLayout mDesContainer;
	
	@ViewInject(R.id.detail_btns_container)
	private FrameLayout mDownloadContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//
		super.onCreate(savedInstanceState);
//		TextView tv = new TextView(this);
//		tv.setText("详情页");
//		initActionBar();
//		setContentView(tv);
		
		mBitmapUtils = new BitmapUtils(this);
		
		Intent intent = getIntent();
		
		Bundle extras = intent.getExtras();
		
		mPackageName = extras.getString("packageName");
//		
		mLoadingUI=new LoadingUI(this) {
			
			@Override
			public View onLoadSuccessView()
			{
				return initSuccessView();
			}
			
			@Override
			public ResultState onLoadData()
			{
				return initData();
			}
		};
		
		//开始获得数据
		mLoadingUI.loadData();
		
		setContentView(mLoadingUI);
		
	}
	
	protected ResultState initData()
	{
		mProtocal=new DetailProtocal(mPackageName);
		try
		{
			mBean = mProtocal.loadData(0);
			
			if(mBean==null){
				return ResultState.EMPTRY;
			}
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			return ResultState.ERROR;
		}
		
		return ResultState.SUCCESS;
	}



	protected View initSuccessView()
	{
		
	
		initActionBar();
		View view = View.inflate(this, R.layout.detail_activity, null);
		//注入
		ViewUtils.inject(this, view);
		
		//信息部分
		DetailAppInfoHolder infoHolder=new DetailAppInfoHolder();
		mAppInfoContainer.addView(infoHolder.getRootView());
		infoHolder.setData(mBean);
		//安全部分
		DetailSafeHolder safeHolder=new DetailSafeHolder();
		mSafeContainer.addView(safeHolder.getRootView());
		safeHolder.setData(mBean);
		//图片部分
		DetailPicsHolder picHolder=new DetailPicsHolder();
		mPicsContainer.addView(picHolder.getRootView());
		picHolder.setData(mBean);
		//简介部分
		DetailDesHolder desHolder=new DetailDesHolder();
		mDesContainer.addView(desHolder.getRootView());
		desHolder.setData(mBean);
		
		//下载图标
		DetailDownHolder downHolder=new DetailDownHolder();
		mDownloadContainer.addView(downHolder.getRootView());
		downHolder.setData(mBean);
		
		
		return view;
	}



	// 初始化actionbar
	private void initActionBar()
	{
		String uri=Constants.BASE_IMAGE+mBean.iconUrl;
		
		Drawable icon=null;
		
		File file = mBitmapUtils.getBitmapFileFromDiskCache(uri);
		if(file.exists()){
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			
			if(bitmap!=null){
				icon=new BitmapDrawable(getResources(),bitmap);
			}
		}
		System.out.println(mBean.name);

		mActionBar = getSupportActionBar();

		mActionBar.setTitle(mBean.name);
		
		mActionBar.setIcon(icon);


		// 设置显示标题
		mActionBar.setDisplayShowTitleEnabled(true);
		// 设置显示顶部返回按钮
		mActionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				break;

			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}

}

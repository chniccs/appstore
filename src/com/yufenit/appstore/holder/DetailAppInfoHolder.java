package com.yufenit.appstore.holder;

import java.io.File;
import java.text.Format;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.activity.DetailActivity;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: DetailAppInfoHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-28 下午2:35:46
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class DetailAppInfoHolder extends BaseHolder<AppInfoBean>
{
	private AppInfoBean	mData;
	
	@ViewInject(R.id.detail_appicon)
	private ImageView mIcon;
	
	@ViewInject(R.id.detail_appname)
	private TextView mName;
	
	@ViewInject(R.id.detail_appstar)
	private RatingBar mStar;
	
	@ViewInject(R.id.detail_app_downNum)
	private TextView mDownNum;
	
	@ViewInject(R.id.detail_app_version)
	private TextView mVersion;
	
	@ViewInject(R.id.detail_app_date)
	private TextView mDate;
	
	@ViewInject(R.id.detail_app_size)
	private TextView mSize;

	private BitmapUtils	mBitmapUtils;

	@Override
	protected View initView()
	{
		mBitmapUtils = new BitmapUtils(UIUtils.getContext());
		
		View view = View.inflate(UIUtils.getContext(), R.layout.detail_appinfo, null);
		// 注入
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	protected void Refresh(AppInfoBean data)
	{
		//设置图标
		String uri=Constants.BASE_IMAGE+data.iconUrl;
		
		File file = mBitmapUtils.getBitmapFileFromDiskCache(uri);
		
		if(file.exists()){
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			
			BitmapDrawable drawable = new BitmapDrawable(UIUtils.getResources(),bitmap);
			mIcon.setImageDrawable(drawable);
		}
		//设置文本信息
		mName.setText(data.name);
		
		//设置评分
		mStar.setRating(data.stars);
		
		//设置下载次数
		mDownNum.setText(UIUtils.getString(R.string.info_downloadnum,data.downloadNum));
		
		//设置版本
		mVersion.setText(UIUtils.getString(R.string.info_version,data.version));
		
		//设置更新日期
		mDate.setText(UIUtils.getString(R.string.info_date,data.date));
		
		//设置大小
		mSize.setText(UIUtils.getString(R.string.info_size,
		                                Formatter.formatFileSize(UIUtils.getContext(), data.size)));
		
		
	}

}

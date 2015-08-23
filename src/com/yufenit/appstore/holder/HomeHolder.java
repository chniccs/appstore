package com.yufenit.appstore.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: HomeHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午10:04:57
 * @描述: 主页面的hodler
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public class HomeHolder extends BaseHolder<AppInfoBean>
{

	@ViewInject(R.id.item_appinfo_iv_icon)
	private ImageView	mIcon;

	@ViewInject(R.id.item_appinfo_tv_title)
	private TextView	mTitle;

	@ViewInject(R.id.item_appinfo_rb_stars)
	private RatingBar	mRatingBar;

	@ViewInject(R.id.item_appinfo_tv_size)
	private TextView	mSize;

	@ViewInject(R.id.item_appinfo_tv_des)
	private TextView	mDes;

	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	protected void Refresh(AppInfoBean data)
	{
		// 设置名称
		mTitle.setText(data.name);
		//评分
		mRatingBar.setRating(data.stars);
		//大小
		mSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
		//描述
		mDes.setText(data.des);
		
		BitmapUtils utils=new BitmapUtils(UIUtils.getContext());
		String uri=Constants.BASE_ICON_URL+data.iconUrl;
		
		utils.display(mIcon, uri);
		
	}

}
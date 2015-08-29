package com.yufenit.appstore.holder;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.holder
 * @类名:	DetailAppInfoHolder
 * @创建者:	chniccs
 * @创建时间:	2015-8-28	下午2:35:46 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	TODO
 */
public class DetailPicsHolder extends BaseHolder<List<String>>
{
	@ViewInject(R.id.detail_pics_container)
	private LinearLayout mContainer;
	
	private BitmapUtils mBitmapUtils;
	
	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.detail_picture, null);
		
		ViewUtils.inject(this, view);
		
		mBitmapUtils=new BitmapUtils(UIUtils.getContext());
		return view;
	}

	@Override
	protected void Refresh(List<String> data)
	{

		ImageView iv = null;
	LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
	                                                               LayoutParams.WRAP_CONTENT);
	
		 params.leftMargin=UIUtils.dp2px(5);
		 params.bottomMargin=UIUtils.dp2px(5);
		 params.topMargin=UIUtils.dp2px(5);
		 params.rightMargin=UIUtils.dp2px(5);
		for (int i = 0; i < data.size(); i++)
		{
			
			String uri=Constants.BASE_IMAGE+data.get(i);
			
			iv = new ImageView(UIUtils.getContext());
			
			mBitmapUtils.display(iv, uri);
			
			mContainer.addView(iv,params);
		}
		
	}

}

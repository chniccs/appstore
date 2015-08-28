package com.yufenit.appstore.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
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
public class DetailDownHolder extends BaseHolder<AppInfoBean>
{

	@Override
	protected View initView()
	{
	
		View view = View.inflate(UIUtils.getContext(), R.layout.detail_btn, null);
		return view;
	}

	@Override
	protected void Refresh(AppInfoBean data)
	{
		// TODO Auto-generated method stub
		
	}

}

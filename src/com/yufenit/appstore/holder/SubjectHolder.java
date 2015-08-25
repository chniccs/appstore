package com.yufenit.appstore.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.SubJectBean;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: HomeHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午10:04:57
 * @描述: 游戏页面的hodler
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public class SubjectHolder extends BaseHolder<SubJectBean>
{

	@ViewInject(R.id.subject_pic)
	private ImageView	mIcon;

	@ViewInject(R.id.subject_des)
	private TextView	mTitle;



	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.subject_item, null);
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	protected void Refresh(SubJectBean data)
	{
		// 设置名称
		mTitle.setText(data.des);
	
		
		BitmapUtils utils=new BitmapUtils(UIUtils.getContext());
		String uri=Constants.BASE_IMAGE+data.url;
		
		utils.display(mIcon, uri);
		
	}

}
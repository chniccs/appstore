package com.yufenit.appstore.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: LoadMoreHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-25 下午12:14:38
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class LoadMoreHolder extends BaseHolder<Integer>
{
	public static final int	STATE_LOAD_ERROR	= 0;	// 加载异常
	public static final int	STATE_LOADING		= 1;	// 加载中
	public static final int	STATE_LOAD_EMPTRY	= 2;	// 加载为空，无更多数据

	@ViewInject(R.id.item_loadmore_container_loading)
	private LinearLayout	mLoadmore;
	@ViewInject(R.id.item_loadmore_container_retry)
	private LinearLayout	mRetry;

	@Override
	protected View initView()
	{
		// TODO Auto-generated method stub

		View view = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);

		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	protected void Refresh(Integer data)
	{
		// 设置加载更多容器显示的状态
		switch (data)
		{
			case STATE_LOAD_ERROR:
				mLoadmore.setVisibility(View.GONE);
				mRetry.setVisibility(View.VISIBLE);
				break;
			case STATE_LOAD_EMPTRY:
				mLoadmore.setVisibility(View.GONE);
				mRetry.setVisibility(View.GONE);
				break;
			case STATE_LOADING:
				mLoadmore.setVisibility(View.VISIBLE);
				mRetry.setVisibility(View.GONE);
				break;

			default:
				break;
		}
	}

}

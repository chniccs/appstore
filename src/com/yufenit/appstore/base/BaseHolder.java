package com.yufenit.appstore.base;

import android.view.View;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.base
 * @类名: BaseHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午8:50:54
 * @描述: Holder的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public abstract class BaseHolder<T>
{

	protected View	mView;

	public BaseHolder() {
		this.mView = initView();
	}

	// 获得view
	public View getRootView()
	{
		return mView;
	}

	// 设置数据
	public void setData(T data)
	{
		Refresh(data);
	}

	/**
	 * 设置展示的view
	 * @return
	 */
	protected abstract View initView();
	/**
	 * 更新UI显示，设置数据
	 * @param data
	 */
	protected abstract void Refresh(T data);
}

package com.yufenit.appstore.base;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.base
 * @类名: ParentAdapter
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午8:36:31
 * @描述: 适配器的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public abstract class ParentAdapter<T> extends BaseAdapter
{
	private List<T>	mDatas;

	public ParentAdapter(List<T> data) {
		mDatas = data;
	}

	@Override
	public int getCount()
	{
		if (mDatas != null) { return mDatas.size(); }
		return 0;
	}

	@Override
	public Object getItem(int position)
	{
		if (mDatas != null) { return mDatas.get(position); }
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		BaseHolder<T> holder = null;
		if (convertView == null)
		{
			holder = getItemHolder();
			// 通过holder来获得view
			convertView = holder.getRootView();

			convertView.setTag(holder);
		}
		else
		{
			holder = (BaseHolder) convertView.getTag();
		}
		// 将数据传输给holder，让它去设置数据展示
		holder.setData(mDatas.get(position));

		return convertView;
	}

	// 让子类去实现对应的holder
	protected abstract BaseHolder<T> getItemHolder();
}

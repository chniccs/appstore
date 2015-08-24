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
	
	private static int VIEW_TYPE_NORMAL=0;//普通item的类型
	
	private static int VIEW_TYPE_LOADMORE=1;//加载更多的item类型
	

	public ParentAdapter(List<T> data) {
		mDatas = data;
	}

	@Override
	public int getCount()
	{
		if (mDatas != null) { return mDatas.size()+1; }
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
	
	//获得显示条目的view类型
	@Override
	public int getItemViewType(int position)
	{
		if(position==getCount()-1){return VIEW_TYPE_LOADMORE;}
		return VIEW_TYPE_NORMAL;
	}
	//获得显示view的种类的数量
	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		int type = getItemViewType(position);
		
		if(type==VIEW_TYPE_LOADMORE){
			//TODO
		}
		
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

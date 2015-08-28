package com.yufenit.appstore.adapter;

import java.util.List;

import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.holder.LoadMoreHolder;
import com.yufenit.appstore.manager.ThreadPoolManager;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

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
	private List<T>			mDatas;

	private static int		VIEW_TYPE_NORMAL	= 0;	// 普通item的类型

	private static int		VIEW_TYPE_LOADMORE	= 1;	// 加载更多的item类型

	private LoadMoreHolder	mLoadMoreHolder;

	private LoadMoreTask	mLoadMoreTask;

	public ParentAdapter(List<T> data) {
		mDatas = data;
	}

	@Override
	public int getCount()
	{
		if (mDatas != null) { return mDatas.size() + 1; }
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

	// 获得显示条目的view类型
	@Override
	public int getItemViewType(int position)
	{
		if (position == getCount() - 1) { return VIEW_TYPE_LOADMORE; }
		return VIEW_TYPE_NORMAL;
	}

	// 获得显示view的种类的数量
	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		int type = getItemViewType(position);

		BaseHolder<T> holder = null;
		// 判断是否是需要加载更多的holder
		if (type == VIEW_TYPE_LOADMORE)
		{
			// 加载更多数据

			holder = (BaseHolder<T>) getLoadMoreHolder();
		}
		else
		{
			holder = getItemHolder(position);
		}

		if (convertView == null)
		{
			// 通过holder来获得view
			convertView = holder.getRootView();

			convertView.setTag(holder);
		}
		else
		{
			holder = (BaseHolder) convertView.getTag();
		}
		// 将数据传输给holder，让它去设置数据展示

		// 判断是设置什么数据
		if (type == VIEW_TYPE_LOADMORE)
		{
			// 设置显示加载更多的数据
			// 先判断是否显示更多数据
			if (getMore())
			{
				// 执行加载
				performLoadMore();

			}
			else
			{
				// 不加载
				mLoadMoreHolder.setData(LoadMoreHolder.STATE_LOAD_EMPTRY);
			}
		}
		else
		{
			holder.setData(mDatas.get(position));
		}

		return convertView;
	}

	/**
	 * 加载更多数据
	 */
	private void performLoadMore()
	{
		// 设置UI显示状态
		mLoadMoreHolder.setData(LoadMoreHolder.STATE_LOADING);
		// 加载数据
		ThreadPoolManager.getLongPool().execute(loadMore());

	}

	/**
	 * 加载更多的方法
	 * 
	 */

	private class LoadMoreTask implements Runnable
	{

		@Override
		public void run()
		{
			// TODO 加载更多数据
			int state = mLoadMoreHolder.STATE_LOADING;

			// 让孩子去实现加载更多的方法
			List<T> loadMoreDatas = null;
			try
			{
				loadMoreDatas = onLoadMoreDatas();

				if (loadMoreDatas == null || loadMoreDatas.size() == 0)
				{
					// 没有更多数据
					state = mLoadMoreHolder.STATE_LOAD_EMPTRY;
					// 更新UI显示 TODO
				}
				else
				{

					if (loadMoreDatas.size() > Constants.PAGE_SIZE)
					{
						// 说明还有更多数据
						state = mLoadMoreHolder.STATE_LOADING;
					}
					else
					{
						state = mLoadMoreHolder.STATE_LOAD_EMPTRY;
					}

				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				state = mLoadMoreHolder.STATE_LOAD_ERROR;
			}

			final int currentSate = state;
			final List<T> moreDatas = loadMoreDatas;
			// 更新UI
			UIUtils.post(new Runnable() {

				@Override
				public void run()
				{
					mLoadMoreHolder.setData(currentSate);
					if (moreDatas != null)
					{
						mDatas.addAll(moreDatas);
						notifyDataSetChanged();
					}
				}
			});
		}
	}

	/**
	 * 孩子如果有加载更多的需求就必须要重写这个方法
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List<T> onLoadMoreDatas() throws Exception
	{
		return null;
	}

	/**
	 * 控制只有一个加载任务执行的方法
	 * 
	 * @return
	 */
	private LoadMoreTask loadMore()
	{

		if (mLoadMoreTask == null)
		{
			mLoadMoreTask = new LoadMoreTask();
		}
		return mLoadMoreTask;
	}

	/**
	 * 是否显示加载更多数据，默认情况下是显示更多，若希望不显示更多就要复写引方法，将返回值设为false
	 * 
	 * @return
	 */
	protected boolean getMore()
	{

		return true;
	}

	/**
	 * 加载更多的holder
	 * 
	 * @return
	 * @return
	 */
	private LoadMoreHolder getLoadMoreHolder()
	{
		if (mLoadMoreHolder == null)
		{
			mLoadMoreHolder = new LoadMoreHolder();
		}
		return mLoadMoreHolder;
	}

	// 让子类去实现对应的holder
	protected abstract BaseHolder<T> getItemHolder(int position);
}

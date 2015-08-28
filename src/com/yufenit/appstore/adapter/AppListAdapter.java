package com.yufenit.appstore.adapter;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yufenit.appstore.activity.DetailActivity;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.holder.AppHolder;
import com.yufenit.appstore.holder.HomeHolder;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.adapter
 * @类名: AppListAdapter
 * @创建者: chniccs
 * @创建时间: 2015-8-27 下午8:13:59
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class AppListAdapter extends ParentAdapter<AppInfoBean> implements OnItemClickListener
{
	private List<AppInfoBean>	mDatas;
	private ListView			mListView;

	public AppListAdapter(List<AppInfoBean> data, ListView listView) {
		super(data);
		this.mDatas=data;
		this.mListView=listView;
		
		if(mListView!=null){
			mListView.setOnItemClickListener(this);
		}
	}

	@Override
	protected BaseHolder<AppInfoBean> getItemHolder(int position)
	{
		return new AppHolder();
	}
	//让子类去复写
	protected List<AppInfoBean> onLoadMoreDatas() throws Exception
	{
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		System.out.println("点击了");
		
		Intent intent=new Intent();
		
		intent.setClass(UIUtils.getContext(), DetailActivity.class);
		
		BaseHolder<AppInfoBean> itemHolder = getItemHolder(position);
		
		if(itemHolder instanceof HomeHolder){
			
			intent.putExtra("packageName", mDatas.get(position-1).packageName);
		}else{
			
			intent.putExtra("packageName", mDatas.get(position).packageName);
		}
		
		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		UIUtils.getContext().startActivity(intent);
		
	}

}

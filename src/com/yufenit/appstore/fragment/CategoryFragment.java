package com.yufenit.appstore.fragment;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yufenit.appstore.R;
import com.yufenit.appstore.adapter.ParentAdapter;
import com.yufenit.appstore.base.BaseFragment;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.base.BaseProtocal;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.HomeBean;
import com.yufenit.appstore.bean.ItemCategaryBean;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.holder.CategoryHolder;
import com.yufenit.appstore.holder.GameHolder;
import com.yufenit.appstore.holder.HomeHolder;
import com.yufenit.appstore.protocal.CategoryProtocal;
import com.yufenit.appstore.protocal.GameProtocal;
import com.yufenit.appstore.protocal.HomeProtocal;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.fragment
 * @类名: HomeFragment
 * @创建者: chniccs
 * @创建时间: 2015-8-22 下午9:13:51
 * @描述: 首页的Fragment
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public class CategoryFragment extends BaseFragment
{

	private List<ItemCategaryBean>	mDatas;

	private CategoryProtocal		mProtocal;

	@Override
	public ResultState onStartLoadData()
	{

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		try
		{

			// 连接网络，此方法是被LoadingUI回调的，本身就处于子线程中，所以不需要开启子线程
			// HttpUtils utils = new HttpUtils();
			//
			// String url = Constants.BASE_URL;
			//
			// RequestParams params = new RequestParams();
			// params.addQueryStringParameter("index", "0");
			//
			// ResponseStream stream = utils.sendSync(HttpMethod.GET, url,
			// params);
			//
			// String json = stream.readString();
			//
			// // 解析JSON
			// Gson gson = new Gson();
			// HomeBean bean = gson.fromJson(json, HomeBean.class);
			// HomeBean bean = BaseProtocal.getHomeProtocal().loadata(0);
			if (mProtocal == null)
			{

				mProtocal = new CategoryProtocal();
			}
			List<ItemCategaryBean> bean = mProtocal.loadData(0);

			if (bean == null) { return ResultState.EMPTRY; }

			if (bean == null || bean.size() == 0) { return ResultState.EMPTRY; }

			mDatas = bean;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// 连接失败
			return ResultState.ERROR;
		}

		onStartSuccessView();

		return ResultState.SUCCESS;
	}

	@Override
	public View onStartSuccessView()
	{
		// 返回加载数据成功时的view方法
		ListView mListView = new ListView(UIUtils.getContext());
		// 设置listview的背景
		mListView.setBackgroundResource(R.color.bg);

		mListView.setAdapter(new CategaryAdapter(mDatas));
		Log.d("onStartSuccessView", "name:"+mDatas.size());

		return mListView;
	}

	public class CategaryAdapter extends ParentAdapter<ItemCategaryBean>
	{

		public CategaryAdapter(List<ItemCategaryBean> data) {
			super(data);
			// TODO Auto-generated constructor stub
		}
		@Override
		public int getCount()
		{
			if(mDatas!=null){
				return mDatas.size();
			}
			
			return 0;
		}

		@Override
		public int getViewTypeCount()
		{
			return  2;
		}
		
		@Override
		public int getItemViewType(int position)
		{
			return mDatas.get(position).type;
		}

		@Override
		protected BaseHolder<ItemCategaryBean> getItemHolder(int position)

		{
			
			return new CategoryHolder(mDatas.get(position).type);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			int type = getItemViewType(position);
			
			CategoryHolder holder=new CategoryHolder(type);
			
			holder.setData(mDatas.get(position));
			
			return holder.getRootView();
		}

		@Override
		protected boolean getMore()
		{
			return false;
		}
	}

	/**
	 * 加载更多数据的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<ItemCategaryBean> loadMoreData(int index) throws Exception
	{

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// // 连接网络，此方法是被LoadingUI回调的，本身就处于子线程中，所以不需要开启子线程
		// HttpUtils utils = new HttpUtils();
		//
		// String url = Constants.BASE_URL;
		//
		// RequestParams params = new RequestParams();
		// params.addQueryStringParameter("index", "" + index);
		//
		// ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);
		//
		// String json = stream.readString();
		//
		// // 解析JSON
		// Gson gson = new Gson();
		//
		// HomeBean bean = gson.fromJson(json, HomeBean.class);

		if (mProtocal == null)
		{

			mProtocal = new CategoryProtocal();
		}
		List<ItemCategaryBean> bean = mProtocal.loadData(index);

		return bean;
	}

}

package com.yufenit.appstore.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.yufenit.appstore.R;
import com.yufenit.appstore.adapter.ParentAdapter;
import com.yufenit.appstore.base.BaseFragment;
import com.yufenit.appstore.bean.SubJectBean;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.holder.SubjectHolder;
import com.yufenit.appstore.protocal.SubjectProtocal;
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
public class SubjectFragment extends BaseFragment
{

	private List<SubJectBean>	mDatas;
	
	private SubjectProtocal mProtocal;

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
//			HttpUtils utils = new HttpUtils();
//
//			String url = Constants.BASE_URL;
//
//			RequestParams params = new RequestParams();
//			params.addQueryStringParameter("index", "0");
//
//			ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);
//
//			String json = stream.readString();
//
//			// 解析JSON
//			Gson gson = new Gson();
//			HomeBean bean = gson.fromJson(json, HomeBean.class);
//			HomeBean bean = BaseProtocal.getHomeProtocal().loadata(0);
			if(mProtocal==null){
				
				mProtocal=new SubjectProtocal();
			}
			List<SubJectBean> bean = mProtocal.loadData(0);

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

		mListView.setAdapter(new SubjectAdapter(mDatas));

		return mListView;
	}

	public class SubjectAdapter extends ParentAdapter<SubJectBean>
	{

		public SubjectAdapter(List<SubJectBean> data) {
			super(data);
		}

		@Override
		protected SubjectHolder getItemHolder(int position)

		{
			return new SubjectHolder();
		}

		// 加载更多数据
		@Override
		protected List<SubJectBean> onLoadMoreDatas() throws Exception
		{

			return loadMoreData(mDatas.size());

		}

	}

	/**
	 * 加载更多数据的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<SubJectBean> loadMoreData(int index) throws Exception
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

		if(mProtocal==null){
			
			mProtocal=new SubjectProtocal();
		}
		List<SubJectBean> bean = mProtocal.loadData(index);

		return bean;
	}

}

package com.yufenit.appstore.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseFragment;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.base.ParentAdapter;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.HomeBean;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.holder.HomeHolder;
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
public class HomeFragment extends BaseFragment
{

	private List<AppInfoBean>	mDatas;
	private List<String>		mPicture;

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

		// 连接网络，此方法是被LoadingUI回调的，本身就处于子线程中，所以不需要开启子线程
		HttpUtils utils = new HttpUtils();

		String url = Constants.BASE_URL;

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("index", "0");

		try
		{
			ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);

			String json = stream.readString();

			// 解析JSON
			Gson gson = new Gson();
			HomeBean bean = gson.fromJson(json, HomeBean.class);

			if (bean == null) { return ResultState.EMPTRY; }

			if (bean.list == null || bean.list.size() == 0) { return ResultState.EMPTRY; }

			if (bean.picture == null || bean.picture.size() == 0) { return ResultState.EMPTRY; }

			mDatas = bean.list;
			mPicture = bean.picture;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//连接失败
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

		mListView.setAdapter(new HomeAdapter(mDatas));

		return mListView;
	}

	public class HomeAdapter extends ParentAdapter<AppInfoBean>
	{

		public HomeAdapter(List<AppInfoBean> data) {
			super(data);
		}

		@Override
		protected BaseHolder<AppInfoBean> getItemHolder()

		{
			return new HomeHolder();
		}
	}

}

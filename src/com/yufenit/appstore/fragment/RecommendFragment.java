package com.yufenit.appstore.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yufenit.appstore.base.BaseFragment;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.protocal.RecommendProtocal;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;
import com.yufenit.appstore.view.ShakeListener;
import com.yufenit.appstore.view.ShakeListener.OnShakeListener;
import com.yufenit.appstore.view.StellarMap;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.fragment
 * @类名: RecommendFragment
 * @创建者: chniccs
 * @创建时间: 2015-8-27 下午7:04:58
 * @描述: 推荐页面
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class RecommendFragment extends BaseFragment
{
	private List<String>		mDatas;
	private RecommendProtocal	mProtocal;
	private Random				rdm	= new Random();
	// 摇一摇监听器
	private ShakeListener		mShake;

	@Override
	public ResultState onStartLoadData()
	{

		if (mProtocal == null)
		{
			mProtocal = new RecommendProtocal();
		}

		try
		{
			mDatas = mProtocal.loadData(0);

			if (mDatas == null || mDatas.size() == 0) { return ResultState.EMPTRY; }
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResultState.ERROR;
		}
		return ResultState.SUCCESS;
	}

	@Override
	public View onStartSuccessView()
	{

		final StellarMap mRootView = new StellarMap(UIUtils.getContext());

		// 设置分区的个数，数值为行列，分散个数为此两个数之积
		mRootView.setRegularity(15, 15);

		mRootView.setInnerPadding(UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8));

		mRootView.setAdapter(new RecommendAdapter());

		// 设置第一个显示
		mRootView.setGroup(0, true);

		mShake = new ShakeListener(UIUtils.getContext());

		mShake.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake()
			{
				int currentGroup = mRootView.getCurrentGroup();

				mRootView.setGroup(++currentGroup, true);

			}
		});

		return mRootView;
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if (mShake != null)
		{
			mShake.pause();
		}

	}

	//当页面不显示时就让监听器暂停工作以节省资源
	@Override
	public void onResume()
	{

		super.onResume();
		if (mShake != null)
		{
			mShake.resume();
		}
	}

	private class RecommendAdapter implements StellarMap.Adapter
	{

		@Override
		public int getGroupCount()
		{
			if (mDatas != null && mDatas.size() != 0)
			{
				// 根本一页显示的个数来设置页面总数
				int i = mDatas.size() / Constants.PAGE_SIZE;

				if (mDatas.size() % Constants.PAGE_SIZE > 0) { return ++i; }

			}
			return 0;
		}

		@Override
		public int getCount(int group)
		{
			int groupCount = getGroupCount();
			if (groupCount - 1 > group)
			{
				return Constants.PAGE_SIZE;
			}
			else
			{
				return mDatas.size() % Constants.PAGE_SIZE;
			}
		}

		@Override
		public View getView(int group, int position, View convertView)
		{
			int red = rdm.nextInt(200) + 20;
			int green = rdm.nextInt(200) + 20;
			int blue = rdm.nextInt(200) + 20;
			int color = Color.rgb(red, green, blue);
			String data;
			if (group > 0)
			{

				data = mDatas.get(group * Constants.PAGE_SIZE + position);
			}
			else
			{
				data = mDatas.get(position);
			}

			TextView tv = new TextView(UIUtils.getContext());

			tv.setText(data);

			tv.setTextSize(rdm.nextFloat() * 10 + 10);

			tv.setTextColor(color);

			return tv;
		}

		@Override
		public int getNextGroupOnPan(int group, float degree)
		{
			return 0;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn)
		{
			return 0;
		}

	}

}

package com.yufenit.appstore.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.holder.GameHolder;
import com.yufenit.appstore.holder.HomeHolder;
import com.yufenit.appstore.protocal.GameProtocal;
import com.yufenit.appstore.protocal.HomeProtocal;
import com.yufenit.appstore.protocal.HotProtocal;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;
import com.yufenit.appstore.view.FlowView;

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
public class HotFragment extends BaseFragment
{

	private List<String>	mDatas;

	private HotProtocal		mProtocal;

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

				mProtocal = new HotProtocal();
			}
			List<String> bean = mProtocal.loadData(0);

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
		Random rdm = new Random();

		FlowView mView = new FlowView(UIUtils.getContext());

		mView.setPadding(6, 6, 6, 6);

		for (int i = 0; i < mDatas.size(); i++)
		{

			int red = rdm.nextInt(200) + 20;
			int green = rdm.nextInt(200) + 20;
			int blue = rdm.nextInt(200) + 20;
			int color = Color.rgb(red, green, blue);
			// **---通过代码来实现圆角矩形的背景设置---

			GradientDrawable pressed = new GradientDrawable();
			// --设置形状
			pressed.setShape(GradientDrawable.RECTANGLE);
			// --设置圆角
			pressed.setCornerRadius(UIUtils.dp2px(8));

			pressed.setColor(Color.GRAY);

			// -*--设置不按下时的背景
			GradientDrawable normal = new GradientDrawable();
			// --设置形状
			normal.setShape(GradientDrawable.RECTANGLE);
			// --设置圆角
			normal.setCornerRadius(UIUtils.dp2px(8));

			normal.setColor(color);

			// --设置selector
			StateListDrawable selector = new StateListDrawable();

			selector.addState(new int[] { android.R.attr.state_pressed }, pressed);
			// --除指定状态外的其它状态都是显示普通的
			selector.addState(new int[] {}, normal);

			// **---通过代码来实现圆角矩形的背景设置---

			TextView tv = new TextView(UIUtils.getContext());

			tv.setTextColor(Color.WHITE);

			tv.setPadding(3, 3, 3, 3);

			tv.setTextSize(rdm.nextInt(6) + 14);

			
			tv.setBackgroundDrawable(selector);

			tv.setGravity(Gravity.CENTER);

			tv.setText(mDatas.get(i));
			final int position = i;

			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{
					Toast.makeText(UIUtils.getContext(), "" + mDatas.get(position),
									Toast.LENGTH_SHORT).show();
				}
			});

			mView.addView(tv);
		}

		return mView;
	}

}

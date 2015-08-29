package com.yufenit.appstore.holder;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: DetailAppInfoHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-28 下午2:35:46
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DetailDesHolder extends BaseHolder<AppInfoBean>
{

	@ViewInject(R.id.app_detail_des_tv_des)
	private TextView	mTvDes;

	@ViewInject(R.id.app_detail_des_tv_author)
	private TextView	mTvAuthor;

	@ViewInject(R.id.app_detail_des_iv_arrow)
	private ImageView	mIvArrow;

	private boolean		isOpened	= true;

	private AppInfoBean	mData;

	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.detail_des, null);

		ViewUtils.inject(this, view);
		
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				toggle(true);
			}
		});

		return view;
	}

	@Override
	protected void Refresh(AppInfoBean data)
	{
		mData = data;

		mTvAuthor.setText(data.author);

		mTvDes.setText(data.des);
		
		ViewHelper.setRotation(mIvArrow, 180);
		toggle(false);

	}

	public void toggle(boolean animation)
	{

		int totalHeight = getTotalHeight(mData.des);

		int smallHeight = get7LineHeight(mData.des);

		if (isOpened)
		{
			int start = totalHeight;
			int end = smallHeight;//
			if (animation)
			{
				// 去---》关闭
				doAnimation(start, end);
				ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0).start();

			}
			else
			{
				LayoutParams params = mTvDes.getLayoutParams();
				params.height = end;
				mTvDes.setLayoutParams(params);

				ViewHelper.setRotation(mIvArrow, 0);
			}
		}
		else
		{
			// 去---》打开
			int start = smallHeight;// 显示7行内容高度
			int end = totalHeight;
			if (animation)
			{
				doAnimation(start, end);

				ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
			}
			else
			{
				LayoutParams params = mTvDes.getLayoutParams();
				params.height = end;
				mTvDes.setLayoutParams(params);

				ViewHelper.setRotation(mIvArrow, 180);
			}
		}
		isOpened = !isOpened;

	}

	private int getTotalHeight(String des)
	{
		TextView tv = new TextView(UIUtils.getContext());
		tv.setText(des);

		tv.measure(MeasureSpec.makeMeasureSpec(mTvDes.getMeasuredWidth(), MeasureSpec.EXACTLY),
					0);

		return tv.getMeasuredHeight();
	}

	private int get7LineHeight(String des)
	{
		TextView tv = new TextView(UIUtils.getContext());
		tv.setText(des);
		tv.setLines(7);

		tv.measure(MeasureSpec.makeMeasureSpec(mTvDes.getMeasuredWidth(), MeasureSpec.EXACTLY),
					0);

		return tv.getMeasuredHeight();
	}
	
	private void doAnimation(int start, int end)
	{
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.setDuration(400);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animator)
			{
				int value = (Integer) animator.getAnimatedValue();

				LayoutParams params = mTvDes.getLayoutParams();
				params.height = value;//
				mTvDes.setLayoutParams(params);
			}
		});

		animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator arg0)
			{
				// 动画结束时，scrollView滚动到底部

				// 循环找scrollView

				ViewParent parent = mView.getParent();

				ScrollView scrollView = null;
				while ((parent != null) && (parent instanceof ViewGroup))
				{
					if (parent instanceof ScrollView)
					{
						// 找到scrollView
						scrollView = (ScrollView) parent;
						break;
					}
					parent = parent.getParent();
				}

				scrollView.fullScroll(View.FOCUS_DOWN);
			}

			@Override
			public void onAnimationCancel(Animator arg0)
			{
				// TODO Auto-generated method stub

			}
		});
		animator.start();
	}

}

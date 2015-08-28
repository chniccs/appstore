package com.yufenit.appstore.holder;

import java.util.List;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.AppInfoBean.AppInfoSafeBean;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.holder
 * @类名:	DetailAppInfoHolder
 * @创建者:	chniccs
 * @创建时间:	2015-8-28	下午2:35:46 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	TODO
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DetailSafeHolder extends BaseHolder<AppInfoBean>
{
	private boolean isOpen=false;
	
	//箭头图标
	@ViewInject(R.id.detail_safe_arrow)
	private ImageView mArrow;
	
	//上部容器
	@ViewInject(R.id.detail_safe_topcontainer)
	private RelativeLayout mTopContainer;
	//上部官方图标部分
	@ViewInject(R.id.safe_official)
	private RelativeLayout mOfficial;
	
	@ViewInject(R.id.detail_safe_iv1)
	private ImageView mIv1;
	
	@ViewInject(R.id.detail_safe_tv1)
	private TextView mTv1;
	//上部安全图标部分
	@ViewInject(R.id.safe_isSafe)
	private RelativeLayout mIsSafe;
	
	@ViewInject(R.id.detail_safe_iv2)
	private ImageView mIv2;
	
	@ViewInject(R.id.detail_safe_tv2)
	private TextView mTv2;
	//上部广告图标部分
	@ViewInject(R.id.safe_hasAD)
	private RelativeLayout mHasAD;
	
	@ViewInject(R.id.detail_safe_iv3)
	private ImageView mIv3;
	
	@ViewInject(R.id.detail_safe_tv3)
	private TextView mTv3;
	
	//下部容器
	@ViewInject(R.id.detail_safe_bottomcontainer)
	private LinearLayout mBottomContainer;
	
	//下部显示第一行
	@ViewInject(R.id.detail_safe_iv_isOfficial)
	private ImageView mIvIsOfficial;
	@ViewInject(R.id.detail_safe_tv_isOfficial)
	private TextView mTvIsOfficial;
	//下部显示第二行
	@ViewInject(R.id.detail_safe_iv_isSafe)
	private ImageView mIvIsSafe;
	@ViewInject(R.id.detail_safe_tv_isSafe)
	private TextView mTvIsSafe;
	//下部显示第三行
	@ViewInject(R.id.detail_safe_iv_hasAD)
	private ImageView mIvIsHasAD;
	@ViewInject(R.id.detail_safe_tv_hasAD)
	private TextView mTvIsHasAD;

	private BitmapUtils	mBitmapUtils;

	private int	mWidth;

	private int	mHeight;

	private int	mTopHeight;
	
	@Override
	protected View initView()
	{
		
		mBitmapUtils = new BitmapUtils(UIUtils.getContext());
		View view = View.inflate(UIUtils.getContext(), R.layout.detail_safe, null);
		
		
		//注入
		ViewUtils.inject(this, view);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				
				toggle();
				
			}
		});
		
		return view;
	}

	@Override
	protected void Refresh(AppInfoBean data)
	{
		mBottomContainer.setVisibility(View.VISIBLE);
		if(data==null){
			return;
		}
		
		List<AppInfoSafeBean> safe = data.safe;
		if(safe!=null&&safe.size()!=0){
			for (int i = 0; i < safe.size(); i++)
			{
				AppInfoSafeBean bean = safe.get(i);
				if(i==0){
					if(safe.get(i).safeDesColor==0){
						//上部
						mIv1.setBackgroundResource(R.drawable.detail_safe_bg_safe);
						mTv1.setText("官方");
					
						
					}else{
						mIv1.setBackgroundResource(R.drawable.detail_safe_bg_dangerous);
						mTv1.setText("非官方");
						
					}
					//下部
					String uri=Constants.BASE_IMAGE+bean.safeDesUrl;
					mBitmapUtils.display(mIvIsOfficial, uri);
					mTvIsOfficial.setText(bean.safeDes);
					
					mOfficial.setVisibility(View.VISIBLE);
				}
				if(i==1){
					if(safe.get(i).safeDesColor==0){
						mIv2.setBackgroundResource(R.drawable.detail_safe_bg_safe);
						mTv2.setText("安全");
					}else{
						mIv2.setBackgroundResource(R.drawable.detail_safe_bg_dangerous);
						mTv2.setText("不安全");
					}
					//下部
					String uri=Constants.BASE_IMAGE+bean.safeDesUrl;
					mBitmapUtils.display(mIvIsSafe, uri);
					mTvIsSafe.setText(bean.safeDes);
					
					mIsSafe.setVisibility(View.VISIBLE);
				}
				if(i==2){
					if(safe.get(i).safeDesColor==0){
						mIv3.setBackgroundResource(R.drawable.detail_safe_bg_safe);
						mTv3.setText("无广告");
					}else{
						mIv3.setBackgroundResource(R.drawable.detail_safe_bg_dangerous);
						mTv3.setText("有广告");
					}
					
					//下部
					String uri=Constants.BASE_IMAGE+bean.safeDesUrl;
					mBitmapUtils.display(mIvIsHasAD, uri);
					mTvIsHasAD.setText(bean.safeDes);
					mHasAD.setVisibility(View.VISIBLE);
				}
			}
		}
		
	}
	
	private void toggle(){
		mTopHeight = mTopContainer.getMeasuredHeight();
		mHeight = mBottomContainer.getMeasuredHeight();
		mWidth = mBottomContainer.getMeasuredWidth();
		animato();
		
		if(isOpen){
			
			ObjectAnimator.ofFloat(mArrow, "rotation", 0,180).start();
			isOpen=!isOpen;
		}else{
			ObjectAnimator.ofFloat(mArrow, "rotation", -180,0).start();
			isOpen=!isOpen;
		}
		
		
	}
	
	private void animato(){
		
		if(isOpen){
			ValueAnimator animator = ValueAnimator.ofInt(0,mHeight);
			animator.setDuration(300);
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation)
				{
					Integer animatedValue = (Integer) animation.getAnimatedValue();
					
					mBottomContainer.layout(0, 0+mTopHeight,mWidth, animatedValue+mTopHeight);
				}
			});
			animator.start();
		}else{
			
			ValueAnimator animator = ValueAnimator.ofInt(mHeight,0);
			animator.setDuration(300);
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation)
				{
					Integer animatedValue = (Integer) animation.getAnimatedValue();
					
					mBottomContainer.layout(0, 0+mTopHeight,mWidth, animatedValue+mTopHeight);
				}
			});
			animator.start();
		}
		
	}

}

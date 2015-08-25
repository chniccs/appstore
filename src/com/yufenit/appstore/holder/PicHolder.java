package com.yufenit.appstore.holder;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: PicHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-25 下午4:26:31
 * @描述: 首页轮播图的Holder
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public class PicHolder extends BaseHolder<List<String>> implements OnPageChangeListener
{
	@ViewInject(R.id.item_home_picture_pager)
	private ViewPager		mViewPager;
	@ViewInject(R.id.item_home_picture_container_indicator)
	private LinearLayout	mPointer;

	private List<String>	mDatas;

	private PicAdapter		mAdapter;
	
	PicAutoChangeTask mPicSwicth = null;

	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.item_home_picture, null);

		ViewUtils.inject(this, view);

		return view;
	}

	private class PicAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mDatas != null) { return Integer.MAX_VALUE; }
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			BitmapUtils utils = new BitmapUtils(UIUtils.getContext());
			ImageView iv = null;

			if (mDatas != null)
			{

				String imageUrl = mDatas.get(position%mDatas.size());

				String uri = Constants.BASE_IMAGE + imageUrl;

				iv = new ImageView(UIUtils.getContext());
				iv.setScaleType(ScaleType.FIT_XY);

				container.addView(iv);

				utils.display(iv, uri);
			}

			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

	}

	@Override
	protected void Refresh(List<String> data)
	{

		this.mDatas = data;

		// 创建导航点
		createIndicator();

		mAdapter = new PicAdapter();
		
		mViewPager.setAdapter(mAdapter);
		
		mAdapter.notifyDataSetChanged();
		//添加页面滑动监听器
		mViewPager.setOnPageChangeListener(this);
		
		//设置点击监听
		mViewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO 
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						if(mPicSwicth!=null){
							mPicSwicth.stop();
							System.out.println("stop");
						}
						break;
					case MotionEvent.ACTION_UP:
						
					case MotionEvent.ACTION_CANCEL:
						mPicSwicth.start();
						break;

					default:
						break;
				}
				return false;
			}
		});
		//开启自动轮播
		
		
		
		if(mPicSwicth==null){
			mPicSwicth=new PicAutoChangeTask();
		}
		
		mPicSwicth.start();

	}
	
	private class PicAutoChangeTask implements Runnable{

		@Override
		public void run()
		{
			int currentItem = mViewPager.getCurrentItem();
			mViewPager.setCurrentItem(++currentItem);
			
			UIUtils.postDelayed(this, 2000);
		}
		
		public void stop(){
			//移除自身
			UIUtils.getMainHandler().removeCallbacks(this);
		}
		public void start(){
			stop();
			UIUtils.postDelayed(this, 2000);
		}
		
	}

	private void createIndicator()
	{

		if (mDatas != null)
		{
			LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
																LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(8, 0, 0, 8);
			for (int i = 0; i < mDatas.size(); i++)
			{
				ImageView iv = new ImageView(UIUtils.getContext());
				if(i==0){
					
					iv.setImageResource(R.drawable.indicator_selected);
				}else{
					
					iv.setImageResource(R.drawable.indicator_normal);
				}
				mPointer.addView(iv, params);
			}
		}
	}
	
	

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		
	}

	@Override
	public void onPageSelected(int position)
	{
		int childCount = mPointer.getChildCount();
		if (mDatas != null)
		{
			for (int i = 0; i <childCount; i++)
			{
				ImageView iv = (ImageView) mPointer.getChildAt(i);
				 iv.setImageResource(position%childCount == i ?
				 R.drawable.indicator_selected : R.drawable.indicator_normal);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// 
		
	}

}
